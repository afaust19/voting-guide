package com.faust.votingguide.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.Cookie;

/**
 * Created by afaust on 7/1/17.
 */
@Controller
@RequestMapping(value = "")
public class HomeController {                                         //handles all requests to and from root route ('/')

    @RequestMapping(value = "", method = RequestMethod.GET)                                       //root route ('/')
    public String index(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            String cookieUsername = cookie.getValue();
            if (cookie.getName().equals("user") && !cookieUsername.equals("")) {
                return "redirect:/dashboard";                                  //read cookies - if logged in (cookie value not empty string), redirect to user dashboard (dashboard route will read cookies and fetch user
            }
        }
        return "redirect:/login";               //if not, redirect to login page
    }
}



