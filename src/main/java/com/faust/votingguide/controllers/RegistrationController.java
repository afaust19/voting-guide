package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.User;
import com.faust.votingguide.models.Ward;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.UserDao;
import com.faust.votingguide.models.data.WardDao;
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

/**
 * Created by afaust on 7/11/17.
 */
@Controller
@RequestMapping(value = "register")
public class RegistrationController {

    @Autowired
    UserDao userDao;

    @Autowired
    WardDao wardDao;

    @Autowired
    BallotDao ballotDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayRegisterForm(Model model, HttpServletRequest request) {

        model.addAttribute("user", new User());
        model.addAttribute("wards", wardDao.findAll());
        System.out.println(wardDao.findAll());
        model.addAttribute("title", "Register");
        return "registration/view";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)   //user should not be able to register if already logged on as another user
    public String processRegisterForm (Model model, @ModelAttribute @Valid User user,
                                       Errors errors, HttpServletResponse response,
                                       String verifyPassword) {

        if (errors.hasErrors()) {
            model.addAttribute(user);
            model.addAttribute("title", "Register");
            model.addAttribute("wards", wardDao.findAll());
            return "registration/view";
        }

        boolean passwordsMatch = true;
        if (user.getPassword() == null || verifyPassword == null
                || !user.getPassword().equals(verifyPassword)) {
            passwordsMatch = false;
            model.addAttribute("verifyError", "Passwords do not match");
            model.addAttribute("title", "Register");
            model.addAttribute("wards", wardDao.findAll());
            return "registration/view";
        }

        User existingUser = userDao.findByUsername(user.getUsername());

        if (existingUser == null) {
            Cookie cookie = new Cookie("user", user.getUsername());
            response.addCookie(cookie);
            userDao.save(user);                 //save user to database

            Ballot newBallot = new Ballot();    //create empty ballot
            ballotDao.save(newBallot);          //save ballot to database

            //set newBallot to current user



            return "redirect:/dashboard";
        }

        model.addAttribute(user);
        model.addAttribute("title", "Register");
        model.addAttribute("error", "Username already exists");
        model.addAttribute("wards", wardDao.findAll());
        return "registration/view";

    }

}
