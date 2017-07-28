package com.faust.votingguide.controllers;
import com.faust.votingguide.models.*;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.MeasureDao;
import com.faust.votingguide.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

        List<String> mayoralCandidates = new ArrayList<>();  //make separate ArrayLists for the data points to get passed into view for google charts
        List<String> comptrollerCandidates = new ArrayList<>();
        List<String> alderman7Candidates = new ArrayList<>();
        List<String> alderman28Candidates = new ArrayList<>();
        List<String> measure1 = new ArrayList<>();
        List<String> measure2 = new ArrayList<>();
        List<String> measure3 = new ArrayList<>();

        List<Double> mayoralPercentages = new ArrayList<>();
        List<Double> comptrollerPercentages = new ArrayList<>();
        List<Double> alderman7Percentages = new ArrayList<>();
        List<Double> alderman28Percentages = new ArrayList<>();
        List<Double> measure1Percentages = new ArrayList<>();
        List<Double> measure2Percentages = new ArrayList<>();
        List<Double> measure3Percentages = new ArrayList<>();

        for (Map.Entry<Candidate, Double> result : allCandidatePercentages.entrySet()) {
            if (result.getKey().getOffice().equals("mayor")) {
                mayoralCandidates.add(result.getKey().getName());
                mayoralPercentages.add(result.getValue());
            }
            if (result.getKey().getOffice().equals("comptroller")) {
                comptrollerCandidates.add(result.getKey().getName());
                comptrollerPercentages.add(result.getValue());
            }
            if (result.getKey().getOffice().equals("alderman")) {       //make dynamic?
                if (result.getKey().getWard().getWardNumber() == 7) {
                    alderman7Candidates.add(result.getKey().getName());
                    alderman7Percentages.add(result.getValue());
                }
                if (result.getKey().getWard().getWardNumber() == 28) {
                    alderman28Candidates.add(result.getKey().getName());
                    alderman28Percentages.add(result.getValue());
                }
            }
        }

        for (Map.Entry<Measure, Double> result : allMeasurePercentages.entrySet()) {  //make dynamic?
            if (result.getKey().getId() == 1) {
                measure1.add("YES");
                measure1.add("NO");
                measure1Percentages.add(result.getValue()); //percentage of "YES" votes
                double noVotes = 100 - (result.getValue()); //percentage of "NO" votes
                measure1Percentages.add(noVotes);
            }
            if (result.getKey().getId() == 2) {
                measure2.add("YES");
                measure2.add("NO");
                measure2Percentages.add(result.getValue()); //percentage of "YES" votes
                double noVotes = 100 - (result.getValue()); //percentage of "NO" votes
                measure2Percentages.add(noVotes);
            }
            if (result.getKey().getId() == 3) {
                measure3.add("YES");
                measure3.add("NO");
                measure3Percentages.add(result.getValue()); //percentage of "YES" votes
                double noVotes = 100 - (result.getValue()); //percentage of "NO" votes
                measure3Percentages.add(noVotes);
            }
        }

        //model.addAttribute("measureResults", allMeasurePercentages);
        //model.addAttribute("candidateResults", allCandidatePercentages);
        model.addAttribute("mayoralCandidates", mayoralCandidates);         //find a way to condense this list
        model.addAttribute("mayoralPercentages", mayoralPercentages);
        model.addAttribute("comptrollerCandidates", comptrollerCandidates);
        model.addAttribute("comptrollerPercentages", comptrollerPercentages);
        model.addAttribute("alderman7Candidates", alderman7Candidates);
        model.addAttribute("alderman7Percentages", alderman7Percentages);
        model.addAttribute("alderman28Candidates", alderman28Candidates);
        model.addAttribute("alderman28Percentages", alderman28Percentages);
        model.addAttribute("measure1", measure1);
        model.addAttribute("measure1Percentages", measure1Percentages);
        model.addAttribute("measure2", measure2);
        model.addAttribute("measure2Percentages", measure2Percentages);
        model.addAttribute("measure3", measure3);
        model.addAttribute("measure3Percentages", measure3Percentages);

        model.addAttribute("title", "Results");

        return "results/view2";

    }
}
