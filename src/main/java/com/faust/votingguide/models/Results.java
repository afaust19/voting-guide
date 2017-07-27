package com.faust.votingguide.models;

import com.faust.votingguide.controllers.BallotController;
import com.faust.votingguide.controllers.ResultsController;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by afaust on 7/24/17.
 */

//@Entity
public class Results {       /*               //rename BallotData?                                                                                               //should not be persistent? since it changes constantly based on user votes

    @Id
    @GeneratedValue
    private int id;

    // Fields

    private HashMap<Candidate, Double> allCandidatePercentages = new HashMap<>();
    private HashMap<Measure, Double> allMeasurePercentages = new HashMap<>();


    public Results() {}

    // Methods - put results calculations here - use ballot arraylist from above

    public String updateResults() {                                                                                     //this should be called every time a user submits a ballot to the database (in ballotController)

        List<Ballot> allBallots = new ArrayList<>();                                                                //lists all ballots that have been cast
        List<Measure> allMeasures = new ArrayList<>();
        List<Candidate> allCandidates = new ArrayList<>();

        List<Candidate> selectedCandidates = new ArrayList<>();                                                          //lists all candidates that have been voted for
        List<Measure> selectedMeasures = new ArrayList<>();
        List<Integer> selectedCandidateIds = new ArrayList<>();
        List<Integer> selectedMeasureIds = new ArrayList<>();//lists all measures that have been voted for

        HashMap<Candidate, Integer> allCandidateVotes = new HashMap<>();
        HashMap<Measure, Integer> allMeasureVotes = new HashMap<>();

        allBallots.addAll(BallotController.getAllBallots());  //used instead of access DAO object from Results class - how?                                                          //fetches and saves a list of ballots from controller every time a user casts a ballot

        allCandidates.addAll(BallotController.getAllCandidates());

        allMeasures.addAll(BallotController.getAllMeasures());


        for (Ballot completedBallot : allBallots) {

            selectedCandidates.addAll(completedBallot.getCandidates());

            if (!completedBallot.getMeasures().isEmpty()) {
                for (Measure measure : completedBallot.getMeasures()) {
                    if (measure != null) {
                        selectedMeasures.add(measure);
                    }
                }
                                                                        //populates above list with all measures that have received votes
            }
        }



        for (Candidate selectedCandidate : selectedCandidates) {
            selectedCandidateIds.add(selectedCandidate.getId());
        }

        if (!selectedMeasures.isEmpty()) {
            for (Measure selectedMeasure : selectedMeasures) {
                selectedMeasureIds.add(selectedMeasure.getId());
            }
        }

        for (Candidate candidate : allCandidates) {
            if (selectedCandidateIds.contains(candidate.getId())) {
                allCandidateVotes.put(candidate, Collections.frequency(selectedCandidateIds, candidate.getId()));
            } else {
                allCandidateVotes.put(candidate, 0);
            }
        }

        if (!selectedMeasureIds.isEmpty()) {
            for (Measure measure : allMeasures) {
                if (selectedMeasureIds.contains(measure.getId())) {
                    allMeasureVotes.put(measure, Collections.frequency(selectedMeasureIds, measure.getId()));
                } else {
                    allMeasureVotes.put(measure, 0);
                }
            }
        }


        // Calculate percentages for each Candidate


        double totalNumberBallots = 0;

        for (int i = 0; i < allBallots.size(); i++) {
            totalNumberBallots += 1;
        }

        double totalNumberBallotsWard7 = 0;            //make this dynamic later
        double totalNumberBallotsWard28 = 0;

        for (Ballot ballot : allBallots) {
            if (ballot.getUser().getWard().getWardNumber() == 7) {
                totalNumberBallotsWard7 += 1;
            }
            if (ballot.getUser().getWard().getWardNumber() == 28) {
                totalNumberBallotsWard28 += 1;
            }
        }


        for (Map.Entry<Candidate, Integer> entry : allCandidateVotes.entrySet()) {

            if (entry.getKey().getOffice().equals("alderman")) {                        //if candidate is alderman

                if (entry.getKey().getWard().getWardNumber() == 7) {                          //change this to dynamic loop later

                    double percentage = (entry.getValue() / totalNumberBallotsWard7) * 100;
                    allCandidatePercentages.put(entry.getKey(), percentage);
                }

                if (entry.getKey().getWard().getWardNumber() == 28) {
                    double percentage = (entry.getValue() / totalNumberBallotsWard28) * 100;
                    allCandidatePercentages.put(entry.getKey(), percentage);
                }

            } else {                                                                        //if candidate is mayor or comptroller
                double percentage = (entry.getValue() / totalNumberBallots) * 100;
                allCandidatePercentages.put(entry.getKey(), percentage);
            }
        }

        // Calculate percentages for each measure - should be yes/no


        for (Map.Entry<Measure, Integer> entry : allMeasureVotes.entrySet()) {
            double percentage = (entry.getValue() / totalNumberBallots) * 100;
            allMeasurePercentages.put(entry.getKey(), percentage);

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

            return "";

    }


    // Getters and Setters


    public int getId() {
        return id;
    }

    public HashMap<Candidate, Double> getAllCandidatePercentages() {
        return allCandidatePercentages;
    }

    public void setAllCandidatePercentages(HashMap<Candidate, Double> allCandidatePercentages) {
        this.allCandidatePercentages = allCandidatePercentages;
    }

    public HashMap<Measure, Double> getAllMeasurePercentages() {
        return allMeasurePercentages;
    }

    public void setAllMeasurePercentages(HashMap<Measure, Double> allMeasurePercentages) {
        this.allMeasurePercentages = allMeasurePercentages;
    }

    */
}


