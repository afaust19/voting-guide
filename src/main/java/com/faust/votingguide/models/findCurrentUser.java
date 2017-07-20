package com.faust.votingguide.models;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.http.Cookie;

/**
 * Created by afaust on 7/19/17.
 */
public class findCurrentUser {

    public static String currentUsername = "";

    public String findUser(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user")) {
                currentUsername += cookie.getValue();
                System.out.println("Look! It works!!!!!!!!!!!!!");
                return currentUsername;
            }
        }
        return "User could not be found";
    }

}


