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
import java.util.ArrayList;


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


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view(Model model) {

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
        return "ballot/view";
    }

    /*@RequestMapping(value = "", method = RequestMethod.POST)
    public String select(@RequestParam int candidateId, Model model) {;
        Candidate selectedCandidate = candidateDao.findOne(candidateId);
        // set candidate in ballot; save ballot in ballotdao

        Ballot newBallot = new Ballot();
        newBallot.setCandidate(selectedCandidate.getName());
        ballotDao.save(newBallot);

        model.addAttribute("candidate", selectedCandidate);

        return "ballot/success";

    }*/
}

