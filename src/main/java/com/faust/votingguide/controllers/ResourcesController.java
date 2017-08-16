package com.faust.votingguide.controllers;

import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.UserDao;
import com.faust.votingguide.models.forms.BallotForm;
import com.faust.votingguide.models.forms.ResourcesForm;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by afaust on 8/15/17.
 */

@Controller
@RequestMapping(value = "resources")
public class ResourcesController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayResources() {

        return "resources/view";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addResources(@ModelAttribute("resourcesFormView") ResourcesForm form,
                               HttpServletRequest request) {

        String newArticle = form.getArticle();

        String newVideo = form.getVideo();

        String newAudio = form.getAudio();


        for (Cookie cookie : request.getCookies()) {  //pass current user object to view?
            if (cookie.getName().equals("user")) {
                String currentUsername = cookie.getValue();
                User currentUser = userDao.findByUsername(currentUsername);

                List<String> articles = currentUser.getArticles();
                articles.add(newArticle);
                currentUser.setArticles(articles);

                List<String> videos = currentUser.getVideos();
                videos.add(newVideo);
                currentUser.setVideos(videos);

                List<String> audio = currentUser.getAudio();
                audio.add(newAudio);
                currentUser.setAudio(audio);


            }

        }
        return "resources/view";
    }
}

