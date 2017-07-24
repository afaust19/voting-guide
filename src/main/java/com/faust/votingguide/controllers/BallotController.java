package com.faust.votingguide.controllers;

import com.faust.votingguide.models.*;
import com.faust.votingguide.models.data.*;
import com.faust.votingguide.models.forms.BallotForm;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.Cookie;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by afaust on 7/5/17.
 */
@Controller
@RequestMapping(value = "ballot")
public class BallotController {

    @Autowired
    UserDao userDao;

    @Autowired
    CandidateDao candidateDao;

    @Autowired
    MeasureDao measureDao;

    @Autowired
    BallotDao ballotDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayBallot(Model model, HttpServletRequest request) {

        for (Cookie cookie : request.getCookies()) {  //pass current user object to view?
            if (cookie.getName().equals("user")) {
                String currentUsername = cookie.getValue();
                User currentUser = userDao.findByUsername(currentUsername);
                if (currentUser.getBallot() != null) {   //ADD LATER (AFTER TESTING)
                    return "ballot/error";     //check if currentUser already has a ballot submitted (check if ballotid is not null - if so, redirect to error)
                }

                Ward currentUserWard = currentUser.getWard();
                int currentUserWardId = currentUserWard.getId();
                model.addAttribute("currentUserWardId", currentUserWardId);  //pass ward to view and go through alderman in view?
            }
        }

        List<Candidate> candidates = new ArrayList<>(); //put this list and measures list at top as global variable? since you are creating similar lists in the POST handler

        for (Candidate candidate : candidateDao.findAll()) {
            candidates.add(candidate);
        }

        List<Measure> measures = new ArrayList<>();

        for (Measure measure : measureDao.findAll()) {
                measures.add(measure);
            }

        BallotForm form = new BallotForm(candidates, measures);
        model.addAttribute("form", form);

        return "ballot/view";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processBallot(@ModelAttribute ("ballotFormView") BallotForm form,
                                HttpServletRequest request) {

        Ballot ballot = new Ballot();

        ballot.setCandidates(form.getCandidates());

        List<Measure> measures = new ArrayList<>();

        if (form.getMeasures() != null) {
            measures.addAll(form.getMeasures());
            }

        ballot.setMeasures(measures);


        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user")) {
                String currentUsername = cookie.getValue();
                User currentUser = userDao.findByUsername(currentUsername);  //finds the user currently logged in
                ballot.setUser(currentUser);                                //sets the user to the ballot object just created above
                currentUser.setBallot(ballot);                      //sets the ballot object to the user object
            }
        }

        ballotDao.save(ballot);

        return "ballot/success";
    }
}



