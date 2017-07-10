package com.faust.votingguide.controllers;

import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.Request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by afaust on 7/2/17.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

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
    public String processLoginForm(Model model, User user, HttpServletResponse response) {

        User existingUser = userDao.findByUsername(user.getUsername());   //need error statement - try statement to catch Null Exception

        if (existingUser.getPassword().equals(user.getPassword())) {
            //model.addAttribute("existingUser", existingUser);
            response.addCookie(new Cookie("existingUser", existingUser.getUsername()));  //why does value have to be a String? how to change to int?
            return "redirect:/user/dashboard";
        }

        model.addAttribute("error", "Invalid password");
        return "user/login";
    }

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String displayDashboard(Model model, HttpServletRequest request) {
        for (Cookie c : request.getCookies()) {                                                                         //get all cookies at user/dashboard, iterate through list
            if (c.getName().equals("existingUser")) {        //and condition for value being empty string                                                           //if the cookie's name is "existingUser"
                if (!c.getValue().equals("")) {
                    User existingUser = userDao.findByUsername(c.getValue());                                               //fetch the existingUser object using the value of the cookie
                    model.addAttribute("title", "Congrats! Cookie found!");
                    model.addAttribute("existingUser", existingUser);
                    return "user/dashboard";
                }
            }
            return "redirect:/user/login";  //if cookie "existingUser" has value ("")
        }
        return "redirect:/user/login";  //if there is no cookie named "existingUser"

    }

        @RequestMapping(value = "logout")
        public String logout(HttpServletResponse response){
            Cookie cookie = new Cookie("existingUser", "");   //to delete a cookie, set the existing cookie ("existingUser") to an empty string - https://kodejava.org/how-do-i-delete-a-cookie-in-servlet/
            response.addCookie(cookie);
            return "redirect:/"; //redirects to home page
        }

    }




