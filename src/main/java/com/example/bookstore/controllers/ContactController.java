package com.example.bookstore.controllers;

import com.example.bookstore.contactMessage.Message;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class ContactController {
    @PostMapping("/contact/submit")
    public String submitContactForm(@ModelAttribute Message message, Model model) {
        return "redirect:/store";
    }
}
