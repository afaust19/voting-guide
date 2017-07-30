package com.faust.votingguide.controllers;


import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.Measure;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.MeasureDao;
import com.faust.votingguide.models.Results;
import javafx.concurrent.WorkerStateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by afaust on 7/5/17.
 */

@Controller
@RequestMapping(value = "results")

public class ResultsController {

    @Autowired
    BallotDao ballotDao;

    @Autowired
    CandidateDao candidateDao;

    @Autowired
    MeasureDao measureDao;

    private static List<Ballot> ballots = new ArrayList<>();
    private static List<Candidate> candidates = new ArrayList<>();
    private static List<Measure> measures = new ArrayList<>();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayResults(Model model) {

        ballots.clear();
        candidates.clear();
        measures.clear();

        for (Ballot ballot : ballotDao.findAll()) {
            ballots.add(ballot);
        }

        for (Candidate candidate : candidateDao.findAll()) {
            candidates.add(candidate);
        }

        for (Measure measure : measureDao.findAll()) {
            measures.add(measure);
        }

        Results.calculateResults();             //calls on Results class to run static method

        model.addAttribute("mayoralCandidates", Results.getMayoralCandidates());        //find a way to condense this list

        System.out.println("END RESULTS CONTROLLER: " + Results.getMayoralCandidates().size());

        model.addAttribute("mayoralPercentages", Results.getMayoralPercentages());
        model.addAttribute("comptrollerCandidates", Results.getComptrollerCandidates());
        model.addAttribute("comptrollerPercentages", Results.getComptrollerPercentages());
        model.addAttribute("alderman7Candidates", Results.getAlderman7Candidates());
        model.addAttribute("alderman7Percentages", Results.getAlderman7Percentages());
        model.addAttribute("alderman28Candidates", Results.getAlderman28Candidates());
        model.addAttribute("alderman28Percentages", Results.getAlderman28Percentages());
        model.addAttribute("measure1", Results.getMeasure1());
        model.addAttribute("measure1Percentages", Results.getMeasure1Percentages());
        model.addAttribute("measure2", Results.getMeasure2());
        model.addAttribute("measure2Percentages", Results.getMeasure2Percentages());
        model.addAttribute("measure3", Results.getMeasure3());
        model.addAttribute("measure3Percentages", Results.getMeasure3Percentages());

        model.addAttribute("title", "Election Results");
        return "results/view3";

    }

    public static List<Ballot> getBallots() {
        return ballots;
    }

    public static void setBallots(List<Ballot> ballots) {
        ResultsController.ballots = ballots;
    }

    public static List<Candidate> getCandidates() {
        return candidates;
    }

    public static void setCandidates(List<Candidate> candidates) {
        ResultsController.candidates = candidates;
    }

    public static List<Measure> getMeasures() {
        return measures;
    }

    public static void setMeasures(List<Measure> measures) {
        ResultsController.measures = measures;
    }
}
