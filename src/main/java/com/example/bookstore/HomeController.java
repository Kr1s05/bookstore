package com.example.bookstore;

import com.example.bookstore.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("user") User user){

        return "home/index";
    }

}
