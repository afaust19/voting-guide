package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Resource;
import com.faust.votingguide.models.ResourceType;
import com.faust.votingguide.models.User;
import com.faust.votingguide.models.data.ResourceDao;
import com.faust.votingguide.models.data.UserDao;
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

    @Autowired
    ResourceDao resourceDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayResources(Model model, HttpServletRequest request) {

        User currentUser = null;          //use this to get currentUser in other Controllers

        for (Cookie cookie : request.getCookies()) {  //pass current user object to view?
            if (cookie.getName().equals("user")) {
                String currentUsername = cookie.getValue();
                currentUser = userDao.findByUsername(currentUsername);
            }
        }

        List<Resource> resources = new ArrayList<>();

        for (Resource resource : resourceDao.findAll()) {
            if (resource.getUser().getId() == (currentUser.getId())) {
                resources.add(resource);
            }
        }

        model.addAttribute("resources", resources);
        return "resources/view";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddResource(Model model, HttpServletRequest request) {

        model.addAttribute("resource", new Resource());
        model.addAttribute("resourceTypes", ResourceType.values());
        return "resources/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRecource(@ModelAttribute Resource newResource, Model model, HttpServletRequest request) {

        User currentUser = null;          //use this to get currentUser in other Controllers

        for (Cookie cookie : request.getCookies()) {  //pass current user object to view?
            if (cookie.getName().equals("user")) {
                String currentUsername = cookie.getValue();
                currentUser = userDao.findByUsername(currentUsername);
            }
        }

        currentUser.addResource(newResource);

        resourceDao.save(newResource);

        return "redirect:";
    }


}

