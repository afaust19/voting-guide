package com.faust.votingguide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by afaust on 7/1/17.
 */
@Controller
@RequestMapping(value = "")
public class HomeController {                                         //handles all requests to and from root route ('/')

    @RequestMapping(value = "")                                       //root route ('/')
    public String index() {                                                                                             //method that returns 'index' view
        return "home/index";
    }


}