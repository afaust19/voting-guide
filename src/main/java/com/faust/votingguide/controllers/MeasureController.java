package com.faust.votingguide.controllers;


import com.faust.votingguide.models.*;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.MeasureDao;
import javafx.concurrent.WorkerStateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by afaust on 7/5/17.
 */

@Controller
@RequestMapping(value = "measures")

public class MeasureController {

    @Autowired
    MeasureDao measureDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("measures", measureDao.findAll());
        model.addAttribute("title", "Measures");
        return "measure/index";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)         //handler for viewing one candidate (use id in url to populate view)
    public String viewOne(Model model, @RequestParam int measure_id) {
        Measure measure = measureDao.findOne(measure_id);
        model.addAttribute("measure", measure);
        model.addAttribute("title", measure.getName());
        return "measure/view";
    }

}
