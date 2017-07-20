package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.User;
import com.faust.votingguide.models.Ward;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.MeasureDao;
import com.faust.votingguide.models.data.UserDao;
import com.faust.votingguide.models.forms.BallotForm;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.Cookie;
import javax.validation.Valid;
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

        //Ballot newBallot = new Ballot(); //create new ballot for user here or upon registration?
        //ballotDao.save(newBallot);
        //BallotForm form = new BallotForm(candidateDao.findAll(), newBallot);

        List<Candidate> candidates = new ArrayList<Candidate>();

        for (Candidate candidate : candidateDao.findAll()) {
            candidates.add(candidate);
        }

        BallotForm form = new BallotForm();
        form.setCandidates(candidates);

        model.addAttribute("form", form);


        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user")) {
                String currentUsername = cookie.getValue();
                User currentUser = userDao.findByUsername(currentUsername);
                Ward currentUserWard = currentUser.getWard();
                int currentUserWardId = currentUserWard.getId();
                model.addAttribute("currentUserWardId", currentUserWardId);  //pass ward to view and go through alderman in view?
            }
        }


        //model.addAttribute("candidates", candidateDao.findAll());
        //model.addAttribute("measures", measureDao.findAll());
        return "ballot/view";
    }
    /*@RequestMapping(value = "", method = RequestMethod.GET)
    public String displayBallot(Model model, HttpServletRequest request) {


        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user")) {
                String currentUsername = cookie.getValue();
                User currentUser = userDao.findByUsername(currentUsername);
                Ward currentUserWard = currentUser.getWard();
                int currentUserWardId = currentUserWard.getId();
                model.addAttribute("currentUserWardId", currentUserWardId);  //pass ward to view and go through alderman in view?
            }
        }

        ArrayList<Candidate> mayoralList = new ArrayList<>();
        ArrayList<Candidate> comptrollerList = new ArrayList<>();
        ArrayList<Candidate> aldermanicList = new ArrayList<>();

        for (Candidate candidate : candidateDao.findAll()) {
            if (candidate.getOffice().equals("mayor")) {
                mayoralList.add(candidate);
            }

            if (candidate.getOffice().equals("comptroller")) {
                comptrollerList.add(candidate);
            }

            if (candidate.getOffice().equals("alderman")) {
                aldermanicList.add(candidate);
            }
        }
        model.addAttribute("mayoralCandidates", mayoralList);
        model.addAttribute("comptrollerCandidates", comptrollerList);
        model.addAttribute("aldermanicCandidates", aldermanicList);
        model.addAttribute("measures", measureDao.findAll());
        return "ballot/view";
    }*/

    //@RequestMapping(value = "", method = RequestMethod.POST)
    //public String processBallot(@ModelAttribute("ballotChoices") @Valid BallotForm form, Model model ) { //BallotChoices refers to an instance of DisplayBallotForm (which contains all ballot objects)


    //add all ballot choices to new ballot

    //Ballot newBallot = new Ballot();   //too repetitive here?

    //System.out.println(form.getMayoralCandidate());

    //newBallot.addCandidateItem(form.getMayoralCandidate());
    //newBallot.addCandidateItem(form.getComptrollerCandidate());
    //newBallot.addCandidateItem(form.getAldermanicCandidate());
    //newBallot.addMeasureItem(form.getMeasure1());
    //newBallot.addMeasureItem(form.getMeasure2());

    //new ballot object should have the current user's id

    // add new ballot to database

    //ballotDao.save(newBallot);
    //return "ballot/success";


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processBallot(@ModelAttribute ("ballotFormView") BallotForm form) {

        System.out.println(form);
        System.out.println(form.getCandidates());

        List<Candidate> candidates = form.getCandidates();

        if (null != candidates && candidates.size() > 0) {
            for (Candidate candidate : candidates) {
                System.out.println(candidate.getName());
            }
        }

        //Ballot ballot = ballotDao.findOne(form.getBallotId());
        //Candidate candidate = candidateDao.findOne(form.getCandidatesIds());

        //ballotDao.save

        return "ballot/success";
    }
}



