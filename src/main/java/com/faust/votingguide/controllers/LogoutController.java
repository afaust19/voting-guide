package com.faust.votingguide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by afaust on 7/11/17.
 */

@Controller
@RequestMapping(value = "logout")
public class LogoutController {

    @RequestMapping(value = "")
    public String logout (HttpServletResponse response){
        Cookie cookie = new Cookie("user", "");   //to delete a cookie, set the existing cookie ("existingUser") to an empty string - https://kodejava.org/how-do-i-delete-a-cookie-in-servlet/
        response.addCookie(cookie);
        return "redirect:/"; //redirects to home page
    }

}
