package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.MeasureDao;
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
@RequestMapping(value = "measures")

public class MeasureController {

    @Autowired
    MeasureDao measureDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("measures", measureDao.findAll());
        model.addAttribute("title", "Measures");
        return "measure/view";
    }

}
