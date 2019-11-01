package com.test.pluto;

import Services.IBasketParagraphService;
import Services.IBasketService;
import classes.BasketParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

  /* @Controller("/basket")
@RequestMapping(value = "/basket")
public class BasketController {

    @Autowired
    private IBasketService myBasket;
    private IBasketParagraphService myBp;

    public BasketController(IBasketService bs, IBasketParagraphService bps) {
        myBasket = bs;
        myBp = bps;
    }

 @RequestMapping(value = "/delete/{bpId}", method={RequestMethod.DELETE})
    @ResponseBody
    public ModelAndView deleteBasketParagraph(@RequestParam @PathVariable("bpId") Long bpId) throws Exception { // returns sessionId
        myBp.deleteBasketParagraph(bpId);
        ModelAndView model = new ModelAndView("basket", "method", "delete"); // пометка,
                                            // что мы удалили книгу из корзины
        return model;
    }
    @RequestMapping(value = "/editNumber/{bpId}/{plusOrMinus}", method={RequestMethod.PATCH})
    @ResponseBody
    public ModelAndView editNumberOfBooks(@RequestParam @PathVariable("bpId") Long bpId, @RequestParam @PathVariable("plusOrMinus") String plusOrMinus) throws Exception { // returns sessionId
        myBp.editNumberOfBooks(bpId, plusOrMinus);
        ModelAndView model = new ModelAndView("basket", "method", plusOrMinus); // пометка, что мы увеличили
                                                // или уменьшили колво книг
        return model;
    }

    @RequestMapping(value = "/clean/{id}", method={RequestMethod.DELETE})
    @ResponseBody
    public ModelAndView cleanBasket(@RequestParam @PathVariable("id") Long id) throws Exception { // returns sessionId
        myBasket.cleanBasket(id);
        ModelAndView model = new ModelAndView("basket", "method", "clean"); // пометка, что мы
                                                //очистили корзину
        return model;
    }




}*/
