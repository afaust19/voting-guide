package com.faust.votingguide.controllers;
import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.Measure;
import com.faust.votingguide.models.Results;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.MeasureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by afaust on 7/23/17.
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


    static List<Ballot> ballots = new ArrayList<>();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayResults(Model model) {

        List<Ballot> ballots = new ArrayList<>();

        HashMap<Candidate, Double> allCandidatePercentages = new HashMap<>();
        HashMap<Measure, Double> allMeasurePercentages = new HashMap<>();

        for (Ballot ballot : ballotDao.findAll()) {
            ballots.add(ballot);
        }

        double totalNumberBallots = 0;

        for (int i = 0; i < ballots.size(); i++) {
            totalNumberBallots += 1;
        }

        double totalNumberBallotsWard7 = 0;            //make this dynamic later
        double totalNumberBallotsWard28 = 0;

        for (Ballot ballot : ballots) {
            if (ballot.getUser().getWard().getWardNumber() == 7) {
                totalNumberBallotsWard7 += 1;
            }
            if (ballot.getUser().getWard().getWardNumber() == 28) {
                totalNumberBallotsWard28 += 1;
            }
        }


        for (Candidate candidate : candidateDao.findAll()) {
            if (candidate.getOffice().equals("alderman")) {

                if (candidate.getWard().getWardNumber() == 7) {
                    double percentage = (candidate.getVotes() / totalNumberBallotsWard7) * 100;
                    allCandidatePercentages.put(candidate, percentage);
                }

                if (candidate.getWard().getWardNumber() == 28) {
                    double percentage = (candidate.getVotes() / totalNumberBallotsWard28) * 100;
                    allCandidatePercentages.put(candidate, percentage);
                }

            }

            double percentage = (candidate.getVotes() / totalNumberBallots) * 100;
            allCandidatePercentages.put(candidate, percentage);
        }

        for (Measure measure : measureDao.findAll()) {
            double percentage = (measure.getVotes() / totalNumberBallots) * 100;
            allMeasurePercentages.put(measure, percentage);
        }


        DecimalFormat newFormat = new DecimalFormat("#.##");                        //rounds the percentage 2 decimal places

        for (Map.Entry<Candidate, Double> result : allCandidatePercentages.entrySet()){
            double twoDecimal = Double.valueOf(newFormat.format(result.getValue()));
            allCandidatePercentages.put(result.getKey(), twoDecimal);
        }

        for (Map.Entry<Measure, Double> result : allMeasurePercentages.entrySet()){
            double twoDecimal = Double.valueOf(newFormat.format(result.getValue()));
            allMeasurePercentages.put(result.getKey(), twoDecimal);
        }

        model.addAttribute("measureResults", allMeasurePercentages);
        model.addAttribute("candidateResults", allCandidatePercentages);
        model.addAttribute("title", "Results");


        return "results/view";

    }
}
