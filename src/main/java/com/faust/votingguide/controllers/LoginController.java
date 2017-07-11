package com.faust.votingguide.controllers;

import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by afaust on 7/11/17.
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)   //need error message to log out if already logged in
    public String displayLoginForm (Model model){
        model.addAttribute("user", new User());
        return "login/view";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLoginForm (Model model, User user, HttpServletResponse response){

        //User existingUser = userDao.findByUsername(user.getUsername());   //need error statement - try statement to catch Null Exception

        HashMap<String, Integer> usernameIdList = new HashMap<>();  //makes a list of all of the usernames in the database - put here or somewhere else for reusability?? How to put this in User class?

        for (User eachUser : userDao.findAll()) {
            usernameIdList.put(eachUser.getUsername(), eachUser.getId());
        }

        int existingUserId = usernameIdList.get(user.getUsername());

        User existingUser = userDao.findOne(existingUserId);

        if (existingUser.getPassword().equals(user.getPassword())) {
            Cookie cookie = new Cookie("user", existingUser.getUsername());
            response.addCookie(cookie);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid password");
        return "login/view";
    }
}
