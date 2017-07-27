package com.faust.votingguide.controllers;

import com.faust.votingguide.models.*;
import com.faust.votingguide.models.data.*;
import com.faust.votingguide.models.forms.BallotForm;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.dom4j.bean.BeanAttributeList;
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


    static List<Ballot> allBallots = new ArrayList<>();

    static List<Candidate> allCandidates = new ArrayList<>();

    static List<Measure> allMeasures = new ArrayList<>();

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

        //put this list and measures list at top as global variable? since you are creating similar lists in the POST handler

        for (Candidate candidate : candidateDao.findAll()) {
            allCandidates.add(candidate);
        }

        for (Measure measure : measureDao.findAll()) {
                allMeasures.add(measure);
            }

        List<Candidate> ballotFormCandidates = new ArrayList<>();
        List<Measure> ballotFormMeasures = new ArrayList<>();

        for (Candidate candidate : candidateDao.findAll()) {
            ballotFormCandidates.add(candidate);
        }

        for (Measure measure : measureDao.findAll()) {
            ballotFormMeasures.add(measure);
        }

        BallotForm form = new BallotForm(ballotFormCandidates, ballotFormMeasures);  //don't use static variable for allCandidate and allMeasures to display ballot - populates the list (creates duplicates of each object) each time a new user looks at a ballot
        model.addAttribute("form", form);

        return "ballot/view";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processBallot(@ModelAttribute ("ballotFormView") BallotForm form,
                                HttpServletRequest request) {

        Ballot ballot = new Ballot();

        ballot.setCandidates(form.getCandidates());

        List<Measure> measures = new ArrayList<>();

        for (Measure measure : form.getMeasures()) {
            if (measure != null) {
                measures.add(measure);
            }
        }

        ballot.setMeasures(measures);

            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("user")) {
                    String currentUsername = cookie.getValue();
                    User currentUser = userDao.findByUsername(currentUsername);                                             //finds the user currently logged in
                    ballot.setUser(currentUser);                                                                            //sets the user to the ballot object just created above
                    currentUser.setBallot(ballot);                                                                          //sets the ballot object to the user object
                }
            }


        ballotDao.save(ballot);

        for (Ballot b : ballotDao.findAll()) {              //don't need this                                                                 //fetches all ballots from database and stores them in static variable above
            allBallots.add(b);
        }


        for (Candidate candidate : form.getCandidates()) {
            candidate.setVotes(candidate.getVotes() + 1);
            candidateDao.save(candidate);
        }

        for (Measure measure : form.getMeasures()) {
            if (measure != null) {
                measure.setVotes(measure.getVotes() + 1);
                measureDao.save(measure);
            }
        }

        //calls method to update voting results (method in results will fetch updated ballot list from ballotController0
        return "ballot/success";
    }

    public static List<Ballot> getAllBallots() {
        return allBallots;
    }

    public static List<Candidate> getAllCandidates() {
        return allCandidates;
    }

    public static List<Measure> getAllMeasures() {
        return allMeasures;
    }

    public BallotDao getBallotDao() {
        return ballotDao;
    }
}




