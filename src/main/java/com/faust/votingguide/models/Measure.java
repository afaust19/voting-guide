package com.faust.votingguide.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.soap.Text;
import java.awt.*;
import java.util.List;

/**
 * Created by afaust on 7/13/17.
 */
@Entity
public class Measure {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String passingCriteria;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String ballotWording;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String summary;

    private int votes = 0;

    @ManyToMany(mappedBy = "measures")
    private List<Ballot> ballots;

    public Measure(int id, String name, String passingCriteria, String ballotWording, String summary, int votes) {
        this.id = id;
        this.name = name;
        this.passingCriteria = passingCriteria;
        this.ballotWording = ballotWording;
        this.summary = summary;
        this.votes = votes;             //marks all measures as "yes" by default, changed once the measure gets put on the user's ballot
    }


    public Measure() {}


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getPassingCriteria() {
        return passingCriteria;
    }

    public String getBallotWording() {
        return ballotWording;
    }

    public String getSummary() {
        return summary;
    }

    public List<Ballot> getBallots() {
        return ballots;
    }


}
