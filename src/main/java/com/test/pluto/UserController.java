package com.test.pluto;

import Services.IBookService;
import Services.IUserService;
import Services.SecurityService;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import validators.UserValidator;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@Controller("/user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService myUser;
    @Autowired
    private IBookService myBook;
    @Autowired
    private SecurityService mySec;
    @Autowired
    private UserValidator myValidator;
    @Autowired
   public UserController(IUserService s, IBookService book, SecurityService sec, UserValidator validator) {
        myUser=s;
        myBook = book;
        mySec = sec;
        myValidator = validator;
    }


    @GetMapping(value="/editUser")
    public String displayEditUser(Model model, Principal principal) {
        model.addAttribute("user", myUser.getUserByEmail(principal.getName()));
        return "editUser";
    }

    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/editUser", method = RequestMethod.POST )
    @ResponseBody
    public String editUser(Principal principal, @ModelAttribute User newUser) throws Exception { // returns sessionId
        myUser.update(newUser);
        return "user";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "new_login";
    }

    @GetMapping(value="/register")
    public String displayRegister(Model model){
        User user = new User();
        model.addAttribute("userForm", user);
        return "registration";
    }

    @PostMapping(value="/register")
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){    // РЕГИСТРАЦИЯ ОБЫЧНОГО ПОЛЬЗОВАТЕЛЯ
        myValidator.validate(userForm, bindingResult);
        System.out.println("user validated");
        if(bindingResult.hasErrors())
            return "registration";
        System.out.println("no errors");
        System.out.println(userForm.toString());
        myUser.save(userForm);
        System.out.println("user added");
        System.out.println(userForm.getEmail() + " " + userForm.getConfirmPassword());
        mySec.autoLogin(userForm.getEmail(), userForm.getConfirmPassword());
        System.out.println("autologinned");
        return "index";
    }

    @RequestMapping(value="")
    public String displayUser() {
        return "user";
    }

    @RolesAllowed(value={"ROLE_ADMIN"})
    @GetMapping(value="admin")
    public String displayAdminPage() {
        return "admin";
    }


    @RolesAllowed(value={"ROLE_ADMIN"})
    @GetMapping(value="/addAdmin")
    public String displayAdmin_addAdmin(ModelMap model) {
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    /*@RolesAllowed(value={"ROLE_ADMIN"})
    @PostMapping(value="/addAdmin")
    public String admin_addAdmin(@ModelAttribute User user, Model model) {  //РЕГИСТРАЦИЯ АДМИНА
        model.addAttribute("message", myUser.register(user.getEmail(), user.getPassword()));
        model.addAttribute("user", new User());
        return displayAdmin_addAdmin(new ModelMap());
    }*/
}

