package com.faust.votingguide.controllers;

import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

/**
 * Created by afaust on 7/2/17.
 */
@Controller
@RequestMapping("dashboard")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayDashboard(Model model, HttpServletRequest request) {

        HashMap<String, Integer> usernameIdList = new HashMap<>();  //makes a list of all of the usernames in the database - put here or somewhere else for reusability?? How to put this in User class?

        for (User eachUser : userDao.findAll()) {
            usernameIdList.put(eachUser.getUsername(), eachUser.getId());
        }

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                String cookieUsername = cookie.getValue();
                if (cookie.getName().equals("user") && !cookieUsername.equals("")) {
                    if (usernameIdList.containsKey(cookieUsername)) {
                        int existingUserId = usernameIdList.get(cookieUsername);
                        User existingUser = userDao.findOne(existingUserId);
                        model.addAttribute("user", existingUser);
                        return "user/dashboard";
                    }
                    return "redirect:/login"; //if there is no cookie "user" with value ""
                }
            }
            return "redirect:/login"; //if there are no cookies
        }
        return "redirect:/login";
    }
}





