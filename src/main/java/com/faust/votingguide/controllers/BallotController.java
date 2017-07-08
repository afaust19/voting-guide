package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.forms.CompareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


/**
 * Created by afaust on 7/5/17.
 */
@Controller
@RequestMapping(value = "ballot")
public class BallotController {

    @Autowired
    CandidateDao candidateDao;

    @Autowired
    BallotDao ballotDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        return "ballot/index";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("candidates", candidateDao.findAll());
        return "ballot/view";
    }

    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String select(@RequestParam int candidateId, Model model) {;
        Candidate selectedCandidate = candidateDao.findOne(candidateId);
        // set candidate in ballot; save ballot in ballotdao

        Ballot newBallot = new Ballot();
        newBallot.setCandidate(selectedCandidate.getName());
        ballotDao.save(newBallot);

        model.addAttribute("candidate", selectedCandidate);

        return "ballot/success";

    }
}

