package com.faust.votingguide.models;

import com.faust.votingguide.controllers.ResultsController;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by afaust on 7/30/17.
 */
@Service
public class Results {

    private static List<String> mayoralCandidates = new ArrayList<>();  //make separate ArrayLists for the data points to get passed into view for google charts
    private static List<String> comptrollerCandidates = new ArrayList<>();
    private static List<String> alderman7Candidates = new ArrayList<>();
    private static List<String> alderman9Candidates = new ArrayList<>();
    private static List<String> measure1 = new ArrayList<>();
    private static List<String> measure2 = new ArrayList<>();
    private static List<String> measure3 = new ArrayList<>();

    private static List<Double> mayoralPercentages = new ArrayList<>();
    private static List<Double> comptrollerPercentages = new ArrayList<>();
    private static List<Double> alderman7Percentages = new ArrayList<>();
    private static List<Double> alderman9Percentages = new ArrayList<>();
    private static List<Double> measure1Percentages = new ArrayList<>();
    private static List<Double> measure2Percentages = new ArrayList<>();
    private static List<Double> measure3Percentages = new ArrayList<>();

    //@Autowired              //these do not autowire - why??
    //BallotDao ballotDao;

    //@Autowired
    //CandidateDao candidateDao;

    //@Autowired
    //MeasureDao measureDao;


    public static void calculateResults() {            //changes fields at top - no return value

        mayoralCandidates.clear();
        comptrollerCandidates.clear();
        alderman7Candidates.clear();
        alderman9Candidates.clear();
        measure1.clear();
        measure2.clear();
        measure3.clear();
        mayoralPercentages.clear();
        comptrollerPercentages.clear();
        alderman7Percentages.clear();
        alderman9Percentages.clear();
        measure1Percentages.clear();
        measure2Percentages.clear();
        measure3Percentages.clear();

        ResultsController resultsController = new ResultsController();

        List<Ballot> ballots = resultsController.getBallots();
        List<Candidate> candidates = resultsController.getCandidates();
        List<Measure> measures = resultsController.getMeasures();

        HashMap<Candidate, Double> allCandidatePercentages = new HashMap<>();
        HashMap<Measure, Double> allMeasurePercentages = new HashMap<>();

        double totalNumberBallots = ballots.size();
        double totalNumberBallotsWard7 = 0;            //make this dynamic later
        double totalNumberBallotsWard9 = 0;

        for (Ballot ballot : ballots) {

            if (ballot.getUser().getWard().getWardNumber() == 7) {
                totalNumberBallotsWard7 += 1;
            }

            if (ballot.getUser().getWard().getWardNumber() == 9) {
                totalNumberBallotsWard9 += 1;
            }
        }

        for (Candidate candidate : candidates) {
            if (candidate.getOffice().equals("alderman")) {

                if (candidate.getWard().getWardNumber() == 7) {
                    double percentage = (candidate.getVotes() / totalNumberBallotsWard7) * 100;
                    allCandidatePercentages.put(candidate, percentage);
                }

                if (candidate.getWard().getWardNumber() == 9) {
                    double percentage = (candidate.getVotes() / totalNumberBallotsWard9) * 100;
                    allCandidatePercentages.put(candidate, percentage);
                }
            }

            if (candidate.getOffice().equals("mayor") || candidate.getOffice().equals("comptroller")) {

                double percentage = (candidate.getVotes() / totalNumberBallots) * 100;
                allCandidatePercentages.put(candidate, percentage);
            }
        }


        for (Measure measure : measures) {
            double percentage = (measure.getVotes() / totalNumberBallots) * 100;
            allMeasurePercentages.put(measure, percentage);
        }

        DecimalFormat newFormat = new DecimalFormat("#.#");                        //rounds the percentage 1 decimal place

        for (Map.Entry<Candidate, Double> result : allCandidatePercentages.entrySet()) {
            double oneDecimal = Double.valueOf(newFormat.format(result.getValue()));
            allCandidatePercentages.put(result.getKey(), oneDecimal);
        }

        for (Map.Entry<Measure, Double> result : allMeasurePercentages.entrySet()) {
            double oneDecimal = Double.valueOf(newFormat.format(result.getValue()));
            allMeasurePercentages.put(result.getKey(), oneDecimal);
        }


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
                if (result.getKey().getWard().getWardNumber() == 9) {
                    alderman9Candidates.add(result.getKey().getName());
                    alderman9Percentages.add(result.getValue());
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
    }

    public static List<String> getMayoralCandidates() {
        return mayoralCandidates;
    }

    public static void setMayoralCandidates(List<String> mayoralCandidates) {
        Results.mayoralCandidates = mayoralCandidates;
    }

    public static List<String> getComptrollerCandidates() {
        return comptrollerCandidates;
    }

    public static void setComptrollerCandidates(List<String> comptrollerCandidates) {
        Results.comptrollerCandidates = comptrollerCandidates;
    }

    public static List<String> getAlderman7Candidates() {
        return alderman7Candidates;
    }

    public static void setAlderman7Candidates(List<String> alderman7Candidates) {
        Results.alderman7Candidates = alderman7Candidates;
    }

    public static List<String> getAlderman9Candidates() {
        return alderman9Candidates;
    }

    public static void setAlderman9Candidates(List<String> alderman28Candidates) {
        Results.alderman9Candidates = alderman28Candidates;
    }

    public static List<String> getMeasure1() {
        return measure1;
    }

    public static void setMeasure1(List<String> measure1) {
        Results.measure1 = measure1;
    }

    public static List<String> getMeasure2() {
        return measure2;
    }

    public static void setMeasure2(List<String> measure2) {
        Results.measure2 = measure2;
    }

    public static List<String> getMeasure3() {
        return measure3;
    }

    public static void setMeasure3(List<String> measure3) {
        Results.measure3 = measure3;
    }

    public static List<Double> getMayoralPercentages() {
        return mayoralPercentages;
    }

    public static void setMayoralPercentages(List<Double> mayoralPercentages) {
        Results.mayoralPercentages = mayoralPercentages;
    }

    public static List<Double> getComptrollerPercentages() {
        return comptrollerPercentages;
    }

    public static void setComptrollerPercentages(List<Double> comptrollerPercentages) {
        Results.comptrollerPercentages = comptrollerPercentages;
    }

    public static List<Double> getAlderman7Percentages() {
        return alderman7Percentages;
    }

    public static void setAlderman7Percentages(List<Double> alderman7Percentages) {
        Results.alderman7Percentages = alderman7Percentages;
    }

    public static List<Double> getAlderman9Percentages() {
        return alderman9Percentages;
    }

    public static void setAlderman9Percentages(List<Double> alderman28Percentages) {
        Results.alderman9Percentages = alderman28Percentages;
    }

    public static List<Double> getMeasure1Percentages() {
        return measure1Percentages;
    }

    public static void setMeasure1Percentages(List<Double> measure1Percentages) {
        Results.measure1Percentages = measure1Percentages;
    }

    public static List<Double> getMeasure2Percentages() {
        return measure2Percentages;
    }

    public static void setMeasure2Percentages(List<Double> measure2Percentages) {
        Results.measure2Percentages = measure2Percentages;
    }

    public static List<Double> getMeasure3Percentages() {
        return measure3Percentages;
    }

    public static void setMeasure3Percentages(List<Double> measure3Percentages) {
        Results.measure3Percentages = measure3Percentages;
    }
}
