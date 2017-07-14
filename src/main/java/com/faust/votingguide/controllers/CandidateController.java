package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Candidate;
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
    public String viewOne(Model model, @RequestParam int candidate_id) {
        Candidate candidate = candidateDao.findOne(candidate_id);
        model.addAttribute("candidate", candidate);
        return "candidate/viewOne";
    }

    @RequestMapping(value = "viewAll", method = RequestMethod.GET)
    public String viewAll(Model model, @RequestParam String office) {

        ArrayList<Candidate> candidateList = new ArrayList<>();

        for (Candidate candidate : candidateDao.findAll()) {
            if (candidate.getOffice().equals(office)) {
                candidateList.add(candidate);
                model.addAttribute("office", office);
                model.addAttribute("candidates", candidateList);
            }
        }
        model.addAttribute("title", office); //capitalize string
        return "candidate/viewAll";
    }

    @RequestMapping(value = "compare", method = RequestMethod.POST)                 //handler for comparing 2 candidates, fetch candidate objects from url passed in from view (candidates.index)
    public String compare(@ModelAttribute("candidatesToCompare") @Valid CompareForm CandidatesToCompare, Model model) { //empty objects? how to bind two objects at once?

        model.addAttribute("candidates", CandidatesToCompare);
        model.addAttribute("title", "Put Office here");

        return "candidate/compare";
    }


    //handler for adding candidates to user ballot
}
