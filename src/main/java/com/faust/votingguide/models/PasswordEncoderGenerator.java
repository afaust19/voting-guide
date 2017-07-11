package com.faust.votingguide.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by afaust on 7/11/17.
 */
public class PasswordEncoderGenerator {

    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            String password = "123456";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println(hashedPassword);
            i++;
        }
    }
}
