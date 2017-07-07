package com.faust.votingguide.models;

import org.hibernate.validator.constraints.Email;

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

    // Fields

    @Id                     //indicates that this should be the primary id (make sure to use javax library)
    @GeneratedValue         //persistence engine (data layer) handles the creation of unique ids for each object
    //@Column(name = "id")    //delete later?
    private int id;

    @NotNull
    @Size(min=3, max=15, message = "Username must be between 3 and 15 characters")
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min=5, message = "Password must contain at least 5 characters")
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;


    public User(String username, String email, String password, String verifyPassword) {
        //this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.verifyPassword = verifyPassword;
    }

    // Default Constructor   -   need this?

    public User() {}

    // Instance Methods

    private void checkPassword() {      //use regular expression later?
        if(!this.password.equals(this.verifyPassword)) {
            verifyPassword = null;
        }
    }

    // Getters and Setters

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
        checkPassword();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

}
