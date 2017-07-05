package com.faust.votingguide.controllers;

import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "candidate/index";
    }
}
