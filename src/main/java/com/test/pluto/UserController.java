package com.test.pluto;

import Services.IBookService;
import Services.IUserService;
import classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller("/user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService myUser;
    @Autowired
    private IBookService myBook;

    @Autowired
   public UserController(IUserService s) {
        myUser=s;
    }

/*
    @GetMapping(value="/editUser")
    public String displayEditUser(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "editUser";
    }
    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/editUser", method = RequestMethod.POST )
    @ResponseBody
    public ModelAndView editUser(HttpSession session, @ModelAttribute User newUser) throws Exception { // returns sessionId
        session.setAttribute("user", newUser);
        ModelAndView model = new ModelAndView("user", "result", myUser.editUser(newUser));
        return model; // возвращает страницу пользователя
    }



    @GetMapping(value="/login")
    public String displayLogin(Model model) {
        User user= new User();
        model.addAttribute("user",user);
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User trueUser=myUser.login(user.getEmail(), user.getPassword());
        try {
            if (!trueUser.equals(null)) {

                User anonUser = (User) session.getAttribute("anonId");
                //myUser.deleteUser(anonUser.getId());
                //session.removeAttribute("anonId");

                session.setAttribute("user", trueUser);
            }
        }
        catch(NullPointerException e)
        {
            model.addAttribute("error", "Похоже, Вы ошиблись с электронной почтой или паролем. Попробуйте еще");
        }
        return "user";
    }

    @GetMapping(value="/register")
    public String displayRegister(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(value="/register")
    public String register(@ModelAttribute("user") User user, Model model){    // РЕГИСТРАЦИЯ ОБЫЧНОГО ПОЛЬЗОВАТЕЛЯ
        model.addAttribute("message", myUser.register(1, user.getEmail(), user.getName(), user.getFullName(), user.getPassword()));
        model.addAttribute("user", new User());
        return "user";
    }

    @RequestMapping(value="")
    public String displayUser() {
        return "user";
    }

    @GetMapping(value="logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("user");
        Map<String, Object> models = new HashMap<>();
        models.put("popularBooks", myBook.getPopularBooks());
        models.put("newArrivals", myBook.getNewArrivals());
        return new ModelAndView("index", "models", models);
    }
    @RolesAllowed(value={"ROLE_ADMIN"})
    @GetMapping(value="addAdmin")
    public String displayAdmin_addAdmin(ModelMap model) {
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }
    @RolesAllowed(value={"ROLE_ADMIN"})
    @PostMapping(value="addAdmin")
    public String admin_addAdmin(@ModelAttribute User user, Model model) {  //РЕГИСТРАЦИЯ АДМИНА
        model.addAttribute("message", myUser.register(2, user.getName(), user.getFullName(), user.getEmail(), user.getPassword()));
        model.addAttribute("user", new User());
        return displayAdmin_addAdmin(new ModelMap());
    }*/
}

