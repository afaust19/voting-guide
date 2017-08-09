package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.Measure;
import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.MeasureDao;
import com.faust.votingguide.models.data.UserDao;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import java.util.HashMap;

/**
 * Created by afaust on 8/1/17.
 */
@Controller
@RequestMapping(value = "history")
public class HistoryController {

    @Autowired
    UserDao userDao;

    @Autowired
    MeasureDao measureDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewHistory(Model model, HttpServletRequest request) {

        HashMap<String, String> measureChoices = new HashMap<>();

        for (Cookie cookie : request.getCookies()) {  //pass current user object to view?
            if (cookie.getName().equals("user")) {
                String currentUsername = cookie.getValue();
                User currentUser = userDao.findByUsername(currentUsername);

                if (currentUser.getBallot() != null) {

                    for (Measure measure : measureDao.findAll()) {
                        if (currentUser.getBallot().getMeasures().contains(measure)) {
                            measureChoices.put(measure.getName(), "YES");
                        } else {
                            measureChoices.put(measure.getName(), "NO");
                        }
                    }



                    model.addAttribute("user", currentUser);
                    model.addAttribute("candidates", currentUser.getBallot().getCandidates());
                    model.addAttribute("measureChoices", measureChoices);
                    return "history/view";

                } else {
                    return "history/error";
                }

            }

        }

        return "login/view";
    }
}

