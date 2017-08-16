package com.faust.votingguide.models;

import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.UserDao;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by afaust on 7/2/17.
 */

@Entity                     //every property (field) in that class will be stored in the database unless you say not to (use @Transient for this)
public class User {

    @Id                     //indicates that this should be the primary id (make sure to use javax library)
    @GeneratedValue         //persistence engine (data layer) handles the creation of unique ids for each object
    private int id;

    @NotNull
    @Size(min=3, max=15, message = "Username must be between 3 and 15 characters")
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min=5, message = "Password must contain at least 5 characters")
    private String password;

    @NotNull
    @ManyToOne
    private Ward ward;           //add as registration form field

    @OneToOne
    @JoinColumn(name = "ballot_id")
    private Ballot ballot;

    @ElementCollection
    @CollectionTable
    private List<String> articles; //list of links

    @ElementCollection
    @CollectionTable
    private List<String> videos;

    @ElementCollection
    @CollectionTable
    private List<String> audio;


    public User(String username, String email, String password, Ward ward) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.ward = ward;
    }

    // Default Constructor   -   need this?

    public User() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {                 //delete this
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Ballot getBallot() {
        return ballot;
    }

    public void setBallot(Ballot ballot) {
        this.ballot = ballot;
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public List<String> getAudio() {
        return audio;
    }

    public void setAudio(List<String> audio) {
        this.audio = audio;
    }
}
