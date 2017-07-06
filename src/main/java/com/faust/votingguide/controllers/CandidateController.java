package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by afaust on 7/5/17.
 */

@Controller
@RequestMapping(value = "candidates")

public class CandidateController {

    @Autowired
    CandidateDao candidateDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("candidates", candidateDao.findAll());
        model.addAttribute("title", "Candidates");
        return "candidate/index";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)         //handler for viewing one candidate (use id in url to populate view)
    public String view(Model model, @RequestParam int candidate_id) {
        Candidate candidate = candidateDao.findOne(candidate_id);
        model.addAttribute("candidate", candidate);

        return "candidate/view";
    }




    //handler for comparing 2 candidates

    //handler for adding candidate to user ballot
}
