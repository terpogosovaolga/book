package com.test.pluto;

import Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller("/user")
@RequestMapping(value = "/user")
public
class UserController {

    @Autowired
    private IUserService myUser;

  //  public UserController(IUserService s) {
        //myUser=s;
  //  }
   /* @RequestMapping(value = "editUser/", method = RequestMethod.POST )
    @ResponseBody
    public ModelAndView editUser(@RequestParam Long id, @RequestParam Map<String,Object> attrs) throws Exception { // returns sessionId


        ModelAndView model = new ModelAndView("user", "result", myUser.editUser(id, attrs));
        return model; // возвращает страницу пользователя
    }
    @RequestMapping(value = "basket/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getBasket(@RequestParam Long id) throws Exception { // returns sessionId

        ModelAndView model = new ModelAndView("basket");
        model.addObject(myUser.getBasket(id));
        return model; // возвращает страницу c корзиной пользователя
    }
    @RequestMapping(value = "pay/", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView pay(@RequestParam String numberOfCard, @RequestParam String name, @RequestParam String cvc) {

        ModelAndView model = new ModelAndView("basket");
        model.addObject(myUser.checkCard(numberOfCard, name, cvc));
        return model; // возвращает страницу c корзиной пользователя
    }*/



}
