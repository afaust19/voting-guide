package com.faust.votingguide;



import org.springframework.boot.autoconfigure.jersey.JerseyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;


/**
 * Created by afaust on 7/11/17.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.faust.votingguide")

public class WebConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

}
