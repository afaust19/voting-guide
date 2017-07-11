package com.faust.votingguide.controllers;

import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by afaust on 7/1/17.
 */
@Controller
@RequestMapping(value = "")
public class HomeController {                                         //handles all requests to and from root route ('/')

    @RequestMapping(value = "", method = RequestMethod.GET)                                       //root route ('/')
    public String index() {
        return "redirect:/login";
    }

}

