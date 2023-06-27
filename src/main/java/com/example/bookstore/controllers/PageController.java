package com.example.bookstore.controllers;

import com.example.bookstore.contactMessage.Message;
import com.example.bookstore.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, @RequestParam(required = false,name = "error") String error) {
        model.addAttribute("user", new User());
        if (error!=null) model.addAttribute("MSG_ERROR", "Invalid username/password!");
        return "login";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("Message", new Message());
        return "contactForm";
    }
}
