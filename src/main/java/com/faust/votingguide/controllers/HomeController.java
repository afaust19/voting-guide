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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by afaust on 7/1/17.
 */
@Controller
@RequestMapping(value = "")
public class HomeController {                                         //handles all requests to and from root route ('/')

    @RequestMapping(value = "", method = RequestMethod.GET)                                       //root route ('/')
    public String index() {
        return "home/index";
    }

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model, HttpServletRequest request) {

        model.addAttribute("user", new User());
        model.addAttribute("title", "Register");
        return "home/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)   //user should not be able to register if already logged on as another user
    public String processRegisterForm (Model model, @ModelAttribute @Valid User user, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute(user);
            model.addAttribute("title", "Register");
            return "home/register";
        }
        //User existingUser = userDao.findByUsername(user.getUsername());

        ArrayList<String> usernameList = new ArrayList<>();  //makes a list of all of the usernames in the database - put here or somewhere else for reusability?? How to put this in User class?

        for (User eachUser : userDao.findAll()) {
            usernameList.add(eachUser.getUsername());
        }

        if (usernameList.contains(user.getUsername())) {  //if existing User exists, give error message and redirect to registration page
            model.addAttribute(user);
            model.addAttribute("title", "Register");
            model.addAttribute("error", "Username already exists");
            return "home/register";
        }

        model.addAttribute((user));
        userDao.save(user);
        return "redirect:";         //redirects to '/'
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)   //need error message to log out if already logged in
    public String displayLoginForm (Model model){
        model.addAttribute("user", new User());
        return "home/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm (Model model, User user, HttpServletResponse response){

        User existingUser = userDao.findByUsername(user.getUsername());   //need error statement - try statement to catch Null Exception

        if (existingUser.getPassword().equals(user.getPassword())) {
            response.addCookie(new Cookie("existingUser", existingUser.getUsername()));  //why does value have to be a String? how to change to int?
            return "redirect:/user/dashboard";
        }

        model.addAttribute("error", "Invalid password");
        return "home/login";
    }

    @RequestMapping(value = "logout")
    public String logout (HttpServletResponse response){
        Cookie cookie = new Cookie("existingUser", "");   //to delete a cookie, set the existing cookie ("existingUser") to an empty string - https://kodejava.org/how-do-i-delete-a-cookie-in-servlet/
        response.addCookie(cookie);
        return "redirect:/"; //redirects to home page
    }

}

