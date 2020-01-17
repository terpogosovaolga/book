package com.test.pluto;

import Services.*;
import classes.*;
import models.Basket;
import models.BasketParagraph;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.ArrayList;
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
    private IUserService myUser;

    /*public BasketController(IBasketService bs, IBasketParagraphService bps) {
        myBasket = bs;
        myBp = bps;
    }*/

    @Autowired
    public BasketController(BasketService basketService, BasketParagraphService basketParagraphService, BookService bookService, UserService userService) {
        myBasket = basketService;
        myBp = basketParagraphService;
        myBs = bookService;
        myUser = userService;
    }


    @RequestMapping(value = "/delete/{bpId}", method={RequestMethod.GET})
    @ResponseBody
    public ModelAndView deleteBasketParagraph(@PathVariable("bpId") Long bpId, Principal principal) throws Exception { // returns sessionId
     BasketParagraph bp = myBp.getBasketParagraphByBasketParagraphId(bpId);
     myBp.delete(bp);
     return getBasket(principal);
    }

    @RequestMapping(value = "/editNumber/{bpId}", method={RequestMethod.POST})
    @ResponseBody
    public ModelAndView editNumberOfBooks(@PathVariable("bpId") Long bpId, int newNumber,  Principal principal) throws Exception { // returns sessionId
        BasketParagraph bp = myBp.getBasketParagraphByBasketParagraphId(bpId);
        myBp.editNumberOfBooks(bp, newNumber);
        return getBasket(principal);
    }

    @RequestMapping(value = "/clean/{bpId}", method={RequestMethod.GET})
    @ResponseBody
    public ModelAndView cleanBasket(@PathVariable("bpId") Long bpId,  Principal principal) throws Exception {
        myBasket.delete(myBasket.getBasketByBasketId(bpId));
        return getBasket(principal); // обновляем страницу и возвращаем новое пустое содержимое
    }
    //ЗАВИСИТ ОТ uSER
    @RequestMapping(method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView getBasket(Principal principal)
    {
        Map<String, Object> map = new HashMap<>();
        ModelAndView model = new ModelAndView("basket");
        User user = myUser.getUserByEmail(principal.getName());
        try {
            Basket basket = myBasket.getBasketByUserId(user.getId());
            map.put("basket", basket);
            map.put("basketParagraphs", myBp.getAllBasketParagraphsByBasket(basket));
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
        bp.setSum(myBs.getBookById(bookId).getCout());
        return new ModelAndView("add", "basketParagraph", bp);
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!---ПРОВЕРИТЬ, ЕСТЬ ЛИ В BASKETPARAGRAPH BASKETID---!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @RequestMapping(value="/add/{bookId}", method=RequestMethod.POST)
    public ModelAndView addBookToBasket(@PathVariable Long bookId, @ModelAttribute BasketParagraph basketParagraph, Principal principal) throws Exception {
        myBp.save(basketParagraph);
        ModelAndView model = new ModelAndView();
        return getBasket(principal);
        /*
        Basket basket;
        User user = myUser.getUserByEmail(principal.getName());
        try {
            basket = myBasket.getBasketByUserId(user.getId());  //БАСКЕТ, ЕСЛИ КОРЗИНА УЖЕ БЫЛА
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
        return getBasket(session);*/
    }

    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value="buy")
    public String displayPayForm(Model model, Principal principal) {
        User user = myUser.getUserByEmail(principal.getName());
        Basket basket = myBasket.getBasketByUserId(user.getId());
        model.addAttribute("card", new Card());
        return "pay";
    }

    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView pay(@ModelAttribute Card card, Principal principal) {
        User user = myUser.getUserByEmail(principal.getName());
        Basket basket = myBasket.getBasketByUserId(user.getId());
        myBasket.setDateOfPurchase(basket, java.util.Calendar.getInstance().getTime());
        return getOrders(principal);
    }

    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value="/orders")
    @ResponseBody
    public ModelAndView getOrders(Principal principal){
        ModelAndView model = new ModelAndView("order");
        Map<String, Object> map = new HashMap<>();
        User user = myUser.getUserByEmail(principal.getName());
        List<Basket> orders = myBasket.getOrdersByUserId(user.getId());
        map.put("orders", orders);
        List<BasketParagraph> bps = new ArrayList<>();
        for (Basket b : orders)
        {
            bps.addAll(myBp.getAllBasketParagraphsByBasket(b));
        }
        map.put("ordersParagraphs", bps);
        model.addObject("map", map);
        return model;
    }


}

