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
            return "redirect:/login";  //if cookie "existingUser" has value ("")
        }
        return "redirect:/login";  //if there is no cookie named "existingUser"

    }

}




