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
        model.addAttribute("mayoralPercentages", Results.getMayoralPercentages());
        model.addAttribute("comptrollerCandidates", Results.getComptrollerCandidates());
        model.addAttribute("comptrollerPercentages", Results.getComptrollerPercentages());
        model.addAttribute("alderman7Candidates", Results.getAlderman7Candidates());
        model.addAttribute("alderman7Percentages", Results.getAlderman7Percentages());
        model.addAttribute("alderman9Candidates", Results.getAlderman9Candidates());
        model.addAttribute("alderman9Percentages", Results.getAlderman9Percentages());
        model.addAttribute("measure1", Results.getMeasure1());
        model.addAttribute("measure1Percentages", Results.getMeasure1Percentages());
        model.addAttribute("measure2", Results.getMeasure2());
        model.addAttribute("measure2Percentages", Results.getMeasure2Percentages());
        model.addAttribute("measure3", Results.getMeasure3());
        model.addAttribute("measure3Percentages", Results.getMeasure3Percentages());
        model.addAttribute("measure4", Results.getMeasure4());
        model.addAttribute("measure4Percentages", Results.getMeasure4Percentages());
        model.addAttribute("measure5", Results.getMeasure5());
        model.addAttribute("measure5Percentages", Results.getMeasure5Percentages());
        model.addAttribute("measure6", Results.getMeasure6());
        model.addAttribute("measure6Percentages", Results.getMeasure6Percentages());


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

    public void setBallotDao(BallotDao ballotDao) {
        this.ballotDao = ballotDao;
    }

    public void setCandidateDao(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }

    public void setMeasureDao(MeasureDao measureDao) {
        this.measureDao = measureDao;
    }
}
