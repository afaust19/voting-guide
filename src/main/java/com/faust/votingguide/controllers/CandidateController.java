package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.forms.CompareForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    @RequestMapping(value = "image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

        File serverFile = new File("/Users/afaust/Desktop/candidatePics/" + imageName + ".jpg");

        return Files.readAllBytes(serverFile.toPath());
    }


    @RequestMapping(value = "view", method = RequestMethod.GET)         //handler for viewing one candidate (use id in url to populate view)
    public String viewOne(Model model, @RequestParam int candidate_id) {
        Candidate candidate = candidateDao.findOne(candidate_id);
        model.addAttribute("candidate", candidate);
        model.addAttribute("title", candidate.getName());
        return "candidate/viewOne";
    }

    @RequestMapping(value = "viewAll", method = RequestMethod.GET)
    public String viewAll(Model model, @RequestParam String office, @RequestParam(required = false) Integer ward) {

        ArrayList<Candidate> candidateList = new ArrayList<>();

        for (Candidate candidate : candidateDao.findAll()) {
            if (candidate.getOffice().equals(office)) {
                if (candidate.getWard() == null) {
                    candidateList.add(candidate);
                    model.addAttribute("candidates", candidateList);
                    model.addAttribute("title", office); //capitalize string
                } else if (candidate.getWard().getWardNumber() == ward) {
                    candidateList.add(candidate);
                    model.addAttribute("candidates", candidateList);
                    model.addAttribute("title", office + " (Ward " + ward + ")");
                }

            }
        }
        return "candidate/viewAll";
    }

    @RequestMapping(value = "compare", method = RequestMethod.POST)                 //handler for comparing 2 candidates, fetch candidate objects from url passed in from view (candidates.index)
    public String compare(@ModelAttribute("candidatesToCompare") @Valid CompareForm CandidatesToCompare, Model model) { //empty objects? how to bind two objects at once?

        model.addAttribute("candidates", CandidatesToCompare);
        model.addAttribute("title", "Compare Candidates for " + CandidatesToCompare.getCompare1().getOffice());

        return "candidate/compare";
    }


    //handler for adding candidates to user ballot
}
