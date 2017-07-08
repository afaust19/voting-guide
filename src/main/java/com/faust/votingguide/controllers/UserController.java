package com.faust.votingguide.controllers;

import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.Request;

import javax.validation.Valid;

/**
 * Created by afaust on 7/2/17.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Welcome!");
        return "user/index";
    }

    //make an index handler for all requests at /user='username'
    //redirect to login page if not logged in

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Register");
        return "user/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegisterForm(Model model, @ModelAttribute @Valid User user, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute(user);
            model.addAttribute("title", "Register");
            return "user/register";
        }

        model.addAttribute((user));
        userDao.save(user);
        return "redirect:";         //redirects to /user
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(Model model, User user) {
        //get a user object from the database, based on username entered into form
        //compare passwords, display errors or redirect to user dashboard if correct
        return "user/login";
    }
}
