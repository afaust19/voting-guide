package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.User;
import com.faust.votingguide.models.Ward;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.MeasureDao;
import com.faust.votingguide.models.data.UserDao;
import com.faust.votingguide.models.forms.CompareForm;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.ArrayList;


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
    }


    /*@RequestMapping(value = "compare", method = RequestMethod.POST)                 //handler for comparing 2 candidates, fetch candidate objects from url passed in from view (candidates.index)
    public String compare(@ModelAttribute("candidatesToCompare") @Valid CompareForm CandidatesToCompare, Model model) { //empty objects? how to bind two objects at once?

        model.addAttribute("candidates", CandidatesToCompare);
        model.addAttribute("title", "Put Office here");

        return "candidate/compare";
    }*/

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processBallot(@ModelAttribute) {



    }

}


