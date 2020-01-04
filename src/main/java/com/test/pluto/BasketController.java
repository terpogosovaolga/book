package com.test.pluto;

import Services.IBasketParagraphService;
import Services.IBasketService;
import Services.IBookService;
import classes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

   /* @RequestMapping(value = "/delete/{bpId}", method={RequestMethod.GET})
    @ResponseBody
    public void addBPInSession(@PathVariable("bpId") Long bpId, HttpSession session) throws Exception { // returns sessionId
        session.setAttribute("basketParagraphId", bpId);

    }*/
/*
 @RequestMapping(value = "/delete/{bpId}", method={RequestMethod.GET})
    @ResponseBody
    public ModelAndView deleteBasketParagraph(@PathVariable("bpId") Long bpId, HttpSession session) throws Exception { // returns sessionId
     System.out.println("id of deleted bp: " + bpId);
     myBp.deleteBasketParagraph(bpId);
     System.out.println("deleted");
     // изменяем общую сумму корзины
     updateCostOfBasket(bpId, session);
     return getBasket(session);
    }

    @RequestMapping(value = "/editNumber/{bpId}", method={RequestMethod.POST})
    @ResponseBody
    public ModelAndView editNumberOfBooks(HttpSession session, @PathVariable("bpId") Long bpId, int newNumber) throws Exception { // returns sessionId
        User user = getUser(session);
        myBp.editNumberOfBooks(bpId, newNumber);
        // изменяем общую сумму корзины
        updateCostOfBasket( myBasket.getBasketByUserId(user.getId()).getId(), session);
        return getBasket(session);
    }

    @RequestMapping(value = "/clean", method={RequestMethod.GET})
    @ResponseBody
    public ModelAndView cleanBasket(HttpSession session) throws Exception {
        User user = getUser(session);
        Basket basket = myBasket.getBasketByUserId(user.getId());
        myBp.deleteBasketParagraphs(basket.getId());
        myBasket.cleanBasket(basket.getId());
        return getBasket(session); // обновляем страницу и возвращаем новое пустое содержимое
    }

    @GetMapping(value="/get")
    @ResponseBody
    public ModelAndView getBasket(HttpSession session)
    {
        //System.out.println("You are in basketController");
        Map<String, Object> map = new HashMap<>();
        ModelAndView model = new ModelAndView("basket");
        User user = getUser(session);
        try {
            System.out.println("ID OF USER: " + user.getId());
            Basket basket = myBasket.getBasketByUserId(user.getId());
            System.out.println("basket: " + basket.getId());
            map.put("basket", basket);
            map.put("basketParagraphs", myBp.getAllBasketParagraphsOfBasket(basket.getId()));
        }
        catch(NullPointerException e)  {
            map.put("message", "Ваша корзина пуста :С");
            System.out.println("empty");
        }
        model.addObject("allBasket", map);
        return model;
    }

    @GetMapping(value="/add/{bookId}")
    public ModelAndView displayAdding(@PathVariable Long bookId){
        BasketParagraph bp = new BasketParagraph(bookId);
        bp.setNumberOfBooks(1);
        System.out.println("book price :" + myBs.getBookById(bookId).getCout());
        bp.setSum(myBs.getBookById(bookId).getCout());
        return new ModelAndView("add", "basketParagraph", bp);
    }

    @RequestMapping(value="/add/{bookId}", method=RequestMethod.POST)
    public ModelAndView addBookToBasket(HttpSession session, @PathVariable Long bookId, @ModelAttribute BasketParagraph basketParagraph) throws Exception {
        System.out.println("-------------------------------------------------------");
        System.out.println("Начинаем добавлять книгу");
        System.out.println("basket paragraph: ");
        System.out.println("its sum : " + basketParagraph.getSum());
        System.out.println("its number of books: " + basketParagraph.getNumberOfBooks());
        Basket basket;
        User user = getUser(session);

        // ПРОВЕРЯЕМ, ЕСТЬ ЛИ У ПОЛЬЗОВАТЕЛЯ КОРЗИНА. ЕСЛИ НЕТ, СОЗДАЕМ ЕЕ
        System.out.println("есть ли корзина у этого пользователя?");
        try {
            System.out.println("проверяем");
            basket = myBasket.getBasketByUserId(user.getId());  //БАСКЕТ, ЕСЛИ КОРЗИНА УЖЕ БЫЛА
            System.out.println("есть. ее айди : " + basket.getId());

            // ПРОВЕРЯЕМ, ЕСТЬ ЛИ В КОРЗИНЕ У ПОЛЬЗОВАТЕЛЯ ПАРАГРАФ С ЭТОЙ КНИГОЙ

            System.out.println("проверим, есть ли БП:");
            System.out.println("its sum : " + basketParagraph.getSum());
            BasketParagraph bp = myBp.getBasketParagraphByBasketAndBook(basket.getId(), basketParagraph.getBookId());
            try {
                //если уже есть такая книга
                if (!bp.equals(null)) {
                    System.out.println("its sum : " + basketParagraph.getSum());
                    //то мы обновляем сумму и количество
                    System.out.println("да, есть. Прежние сумма: " + bp.getSum() + "Прежнее количество: " + bp.getNumberOfBooks());
                    myBp.setPrice(bp.getId(), bp.getSum()+basketParagraph.getSum());
                    System.out.println("Новая сумма бп: " + bp.getSum()+basketParagraph.getSum());
                    System.out.println("its sum : " + basketParagraph.getSum());
                    myBp.editNumberOfBooks(bp.getId(), bp.getNumberOfBooks()+basketParagraph.getNumberOfBooks());
                    System.out.println("Новое кол-во: " +bp.getNumberOfBooks()+basketParagraph.getNumberOfBooks() );
                }
            }
            //если такой книги нет
            catch(NullPointerException e)
            {
                System.out.println("its sum : " + basketParagraph.getSum());
                System.out.println("нет");
                //мы создаем нужный баскетпараграф , указывая айди баскета
                basketParagraph.setBasketId(basket.getId());
                System.out.println("теперь баскет айди: " + basketParagraph.getBasketId());
                myBp.createBasketParagraph(basketParagraph);
                System.out.println("БП создан в бд");
                System.out.println("its sum : " + basketParagraph.getSum());
            }
        }
        //если нет такой корзины
        catch(NullPointerException e)
        {
            System.out.println("нет корзины! ");
            System.out.println("its sum : " + basketParagraph.getSum());
            //создаем корзину и указываем ее айди в basket
            myBasket.createBasket(user.getId());
            System.out.println("создали");
            System.out.println("its sum : " + basketParagraph.getSum());
            basket = myBasket.getBasketByUserId(user.getId());   //баскет, если корзину только создали
            basketParagraph.setBasketId(basket.getId());
            System.out.println("теперь ее айди: " + basket.getId());
            System.out.println("its sum : " + basketParagraph.getSum());
            myBp.createBasketParagraph(basketParagraph);
            System.out.println("БП создан в бд");
        }
        updateCostOfBasket(basket.getId(), session);
        return getBasket(session);
    }

    @GetMapping(value="buy")
    public String displayPayForm(Model model, HttpSession session) {
        User user = getUser(session);
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
        User user = getUser(session);
        List<Basket> orders = myBasket.getOrdersByUserId(user.getId());
        map.put("orders", orders);
        map.put("ordersParagraphs", myBp.getAllBasketParagraphsOfOrders(user.getId()));
        model.addObject("map", map);
        return model;
    }


    private void updateCostOfBasket(Long basketId, HttpSession session) throws Exception {
        List<BasketParagraphBooked> allBp = myBp.getAllBasketParagraphsOfBasket(basketId);
        Double cout = 0.0;
        System.out.println("updating basketId......" + basketId);
        System.out.println("Вы в апдейте");
        if (allBp.size()==0) {
            System.out.println("нет параграфов. сумма 0");
            cleanBasket(session);
        }
        else {
            System.out.println("Начинаем обнволять стоимость корзины!");
            for (BasketParagraphBooked bpb : allBp) {
                System.out.println("Paragraph id: " + bpb.getId() + " its sum: " + bpb.getSum());
                cout += bpb.getSum();
                System.out.println("new sum after " + bpb.getId() + ": " + cout);
            }
            myBasket.updateCostOfBasket(basketId, cout);
        }
    }


    private User getUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (!user.equals(null)) {
            }
        }
        catch(NullPointerException e) {
            user = (User) session.getAttribute("anonId");
        }
        return user;
    }*/
}

