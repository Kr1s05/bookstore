package com.example.bookstore.controllers;

import com.example.bookstore.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


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
    public String login(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}
