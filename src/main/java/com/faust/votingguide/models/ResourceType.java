package com.faust.votingguide.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by afaust on 8/17/17.
 */

public enum ResourceType {

    // Enum values in all caps, display name in () - default constructor is automatically invoked; you cannnot invoke an enum constructor yourself


    ARTICLE ("Article"),
    VIDEO ("Video"),
    AUDIO ("Audio");

    // Fields

    private final String name; // final because values of enums do not change

    // Default Constructor

    ResourceType(String name) {  //allows enum to be created with a String name (display name)
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

