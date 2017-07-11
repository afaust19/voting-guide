package com.faust.votingguide.controllers;

import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * Created by afaust on 7/11/17.
 */
@Controller
@RequestMapping(value = "register")
public class RegistrationController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayRegisterForm(Model model, HttpServletRequest request) {

        model.addAttribute("user", new User());
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
            return "registration/view";
        }

        boolean passwordsMatch = true;
        if (user.getPassword() == null || verifyPassword == null
                || !user.getPassword().equals(verifyPassword)) {
            passwordsMatch = false;
            model.addAttribute("verifyError", "Passwords do not match");
            return "registration/view";
        }

        User existingUser = userDao.findByUsername(user.getUsername());

        if (existingUser == null) {
            Cookie cookie = new Cookie("user", user.getUsername());
            response.addCookie(cookie);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return "redirect:/dashboard";
        }

        model.addAttribute(user);
        model.addAttribute("title", "Register");
        model.addAttribute("error", "Username already exists");
        return "registration/view";

    }



                                                                               //model binding matches a new user object with fields in the view (e.g. user.username, user.password, user.email)?
//boolean passwordsMatch = true;                                                                                        //creates new local variable "passwordsMatch" set to true
//if (user.getPassword() == null || verify == null                                                                      //if the user's entered password is empty, OR the user's entered verify is empty, OR the user's entered password does not equal the user's entered verify
//        || !user.getPassword().equals(verify)) {
//    passwordsMatch = false;                                                                                           //set passwordsMatch to false
//    user.setPassword("");
//    model.addAttribute("verifyError", "Passwords must match");                                                        //pass "verifyError" attribute to view
//}

//if (passwordsMatch) {                                                                                                 //if passwordsMatch still equals True
//    return "user/index";                                                                                              //render user/index.html
//}

//    return "user/add";   //render user/add.html


}
