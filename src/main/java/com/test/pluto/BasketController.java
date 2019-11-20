package com.test.pluto;

import Services.IBasketParagraphService;
import Services.IBasketService;
import Services.IBookService;
import classes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/basket")
@RequestMapping(value = "/basket")
public class BasketController {

    @Autowired
    private IBasketService myBasket;
    @Autowired
    private IBasketParagraphService myBp;
    @Autowired
    private IBookService myBs;

    @Autowired
    public BasketController(IBasketService bs, IBasketParagraphService bps) {
        myBasket = bs;
        myBp = bps;
    }
 @RequestMapping(value = "/delete/{bpId}", method={RequestMethod.DELETE})
    @ResponseBody
    public ModelAndView deleteBasketParagraph( @PathVariable("bpId") Long bpId, HttpSession session) throws Exception { // returns sessionId
     myBp.deleteBasketParagraph(bpId);
     User user = (User) session.getAttribute("user");
     // изменяем общую сумму корзины
     updateCostOfBasket(bpId, user.getId());
     return getBasket(session);
    }

    @RequestMapping(value = "/editNumber/{bpId}/{plusOrMinus}", method={RequestMethod.POST})
    @ResponseBody
    public ModelAndView editNumberOfBooks(HttpSession session, @PathVariable("bpId") Long bpId, @PathVariable("plusOrMinus") String plusOrMinus) throws Exception { // returns sessionId
        User user = (User) session.getAttribute("user");
        myBp.editNumberOfBooks(bpId, plusOrMinus);
        // изменяем общую сумму корзины
        updateCostOfBasket(bpId, user.getId());
        return getBasket(session);
    }

    @RequestMapping(value = "/clean", method={RequestMethod.DELETE})
    @ResponseBody
    public ModelAndView cleanBasket(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        Basket basket = myBasket.getBasketByUserId(user.getId());
        myBp.deleteBasketParagraphs(basket.getId());
        myBasket.cleanBasket(basket.getId());
        return getBasket(session); // обновляем страницу и возвращаем новое пустое содержимое
    }

    @GetMapping(value="/get")
    @ResponseBody
    public ModelAndView getBasket(HttpSession session)
    {
        System.out.println("You are in basketController");
        Map<String, Object> map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        ModelAndView model = new ModelAndView("basket");
        try {
            if (!user.equals(null)) {

            }
        }
        catch(NullPointerException e) {
            user = (User) session.getAttribute("anonId");
        }
        try {
            Basket basket = myBasket.getBasketByUserId(user.getId());
            map.put("basket", basket);
            map.put("basketParagraphs", myBp.getAllBasketParagraphsOfBasket(basket.getId()));
        }
        catch(NullPointerException e)  {
            map.put("message", "Ваша корзина пуста :С");
        }
        model.addObject("allBasket", map);
        return model;
    }

    @GetMapping(value="/add/{bookId}")
    public ModelAndView displayAdding(@PathVariable Long bookId){
        BasketParagraph bp = new BasketParagraph(bookId);
        bp.setNumberOfBooks(1);
        bp.setSum(myBs.getBookById(bookId).getCout());
        return new ModelAndView("add", "basketParagraph", bp);
    }

    @RequestMapping(value="/add/{bookId}", method=RequestMethod.POST)
    public ModelAndView addBookToBasket(HttpSession session, @PathVariable Long bookId, @ModelAttribute BasketParagraph basketParagraph) {
        Basket basket;

        // ОПРЕДЕЛЯЕМ, ЗАЛОГИНЕН ПОЛЬЗОВАТЕЛЬ ИЛИ НЕТ
        User user = (User)session.getAttribute("user");
        try {
            user.getId();
            System.out.println("авторизировано");
        }
        catch(NullPointerException e)
        {
            System.out.println("не авторизировано");
            user = (User) session.getAttribute("anonId");
        }

        // ПРОВЕРЯЕМ, ЕСТЬ ЛИ У ПОЛЬЗОВАТЕЛЯ КОРЗИНА. ЕСЛИ НЕТ, СОЗДАЕМ ЕЕ
        try {
            basket = myBasket.getBasketByUserId(user.getId());
            System.out.println("basket id: " + basket.getId());
        }
        catch(NullPointerException e)
        {
            myBasket.createBasket(user.getId());
            basket = myBasket.getBasketByUserId(user.getId());
        }
        basketParagraph.setBasketId(basket.getId());

        // ПРОВЕРЯЕМ, ЕСТЬ ЛИ В КОРЗИНЕ У ПОЛЬЗОВАТЕЛЯ ПАРАГРАФ С ЭТОЙ КНИГОЙ
        BasketParagraph bp = myBp.getBasketParagraphByBasketAndBook(basketParagraph.getBasketId(), basketParagraph.getBookId());
        try {
            if (!bp.equals(null)) {
               bp.setSum(bp.getSum()+basketParagraph.getSum());
               bp.setNumberOfBooks(bp.getNumberOfBooks()+basketParagraph.getNumberOfBooks());
            }
        }
        catch(NullPointerException e)
        {
            myBp.createBasketParagraph(basketParagraph);
        }
        updateCostOfBasket(basketParagraph.getBasketId(), user.getId());
        return getBasket(session);
    }

    @GetMapping(value="buy")
    public String displayPayForm(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        try {
            user.getId();
            System.out.println("авторизировано");
        }
        catch(NullPointerException e)
        {
            System.out.println("не авторизировано");
            user = (User) session.getAttribute("anonId");
        }
        Basket basket = myBasket.getBasketByUserId(user.getId());
        session.setAttribute("basket", basket);
        model.addAttribute("card", new Card());
        return "pay";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView pay(@ModelAttribute Card card, HttpSession session) {
        Basket basket = (Basket) session.getAttribute("basket");
        myBasket.setDateOfPurchase(basket.getId(), java.util.Calendar.getInstance().getTime());
        session.removeAttribute("basket");
        return getOrders(session);
    }

    @GetMapping(value="/orders")
    @ResponseBody
    public ModelAndView getOrders(HttpSession session){
        ModelAndView model = new ModelAndView("order");
        Map<String, Object> map = new HashMap<>();
        User user = (User)session.getAttribute("user");
        try {
            user.getId();
            System.out.println("авторизировано");
        }
        catch(NullPointerException e)
        {
            System.out.println("не авторизировано");
            user = (User) session.getAttribute("anonId");
        }
        List<Basket> orders = myBasket.getOrdersByUserId(user.getId());
        map.put("orders", orders);
        map.put("ordersParagraphs", myBp.getAllBasketParagraphsOfOrders(user.getId()));
        model.addObject("map", map);
        return model;
    }


    private void updateCostOfBasket(Long basketId, Long userId) {
        List<BasketParagraphBooked> allBp = myBp.getAllBasketParagraphsOfBasket(basketId);
        Double cout = 0.0;
        for (BasketParagraphBooked bpb : allBp)
        {
            cout+=bpb.getSum();
        }
        myBasket.updateCostOfBasket(userId, cout);
    }

}
