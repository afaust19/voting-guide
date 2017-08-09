package com.faust.votingguide.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by afaust on 7/5/17.
 */
@Entity
public class Candidate {

    // Fields - "A candidate has a..."

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String office;

    @NotNull
    private String name;

    @NotNull
    private String party;

    @NotNull
    private String website;

    @NotNull
    private String education;

    @NotNull
    private String electedOffices;   //change to ArrayList of Strings

    @NotNull
    private String incumbent;        //boolean instead?

    private int votes = 0;

    @ManyToOne
    private Ward ward;

    @ManyToMany(mappedBy = "candidates")
    private List<Ballot> ballots;

    // latest Tweet (embedded) - add to resources page?

    public Candidate(String office, String name, String party, String website,
                     String education, String electedOffices,
                     String incumbent, int votes) {
        this.office = office;
        this.name = name;
        this.party = party;
        this.website = website;
        this.education = education;
        this.electedOffices = electedOffices;
        this.incumbent = incumbent;
        this.votes = votes;
    }


    public Candidate() {}

    public boolean equals(Candidate candidate) {
        return (this.id == candidate.id);
    }


    public int getId() {
        return id;
    }

    public String getOffice() {
        return office;
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

    public String getParty() {
        return party;
    }

    public String getWebsite() {
        return website;
    }

    public String getEducation() {
        return education;
    }

    public String getElectedOffices() {
        return electedOffices;
    }

    public String getIncumbent() {
        return incumbent;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public void setBallots(List<Ballot> ballots) {
        this.ballots = ballots;
    }

}
