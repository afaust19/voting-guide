package com.faust.votingguide.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
    private int age;

    @NotNull
    private String website;

    @NotNull
    private String education;

    @NotNull
    private String electedOffices;   //how to change to list/CSV?

    @NotNull
    private String incumbent;        //boolean instead?

    // latest Tweet (embedded)

    // Constructor

    public Candidate(String office, String name, String party, int age, String website,
                     String education, String electedOffices,
                     String incumbent) {
        this.office = office;
        this.name = name;
        this.party = party;
        this.age = age;
        this.website = website;
        this.education = education;
        this.electedOffices = electedOffices;
        this.incumbent = incumbent;
    }

    // Default Constructor

    public Candidate() {}

    // Getters and Setters (no setters because user should not be able to change?)

    public int getId() {
        return id;
    }

    public String getOffice() {
        return office;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public int getAge() {
        return age;
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
}
