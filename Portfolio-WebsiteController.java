package com.eduard.website.controller;

import com.eduard.website.service.GreetingService;
import com.eduard.website.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class WebsiteController {

    @Autowired
    private MailService mailService;

    @GetMapping("/")
    public String getHome(HttpServletRequest request, Model model) {

        System.out.println("Remote Host: " + request.getRemoteHost());
        System.out.println("Remote Addr: " + request.getRemoteAddr());
        System.out.println("Remote Header: " + request.getHeader("X-FORWARDED-FOR"));

        System.out.println();

        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(value);
        }

        GreetingService.sayGreeting(model);

        return "home";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }

    @GetMapping("/projects")
    public String getProjects() {
        return "projects";
    }

    @GetMapping("/projects/portfolio")
    public String getProjectsPortfolio() {
        return "projects-portfolio";
    }

    @GetMapping("/projects/xo")
    public String getProjectsXO() {
        return "projects-xo";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }

    @GetMapping("/sendMail")
    public String sendMail(HttpServletRequest request, Model model) {

        try {
            // Read data from HTML form
            String name = request.getParameter("nameForm");
            String subject = request.getParameter("subjectForm");
            String email = request.getParameter("emailForm");
            String message = request.getParameter("messageForm");

            String text = "New message from Portfolio Website:"
                        + "\n\n" + "Name: " + name
                        + "\n" + "Email: " + email
                        + "\n\n" + "Subject: \n" + subject
                        + "\n\n" + "Message: \n" + message;

            System.out.println(text);

            new Thread(() -> {
                mailService.send(subject, text);
            }).start();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "sent";
    }
    
}
