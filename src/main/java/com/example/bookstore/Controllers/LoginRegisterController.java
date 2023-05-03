package com.example.bookstore.Controllers;

import com.example.bookstore.Exceptions.RegisterException;
import com.example.bookstore.user.User;
import com.example.bookstore.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class LoginRegisterController {
    final
    UserService userService;

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("user") @Valid User user, Model model, BindingResult result){
        if (result.hasErrors()) {
            model.addAttribute("MSG_ERROR","Invalid email address.");
            return "register";
        }
        try {
            userService.registerUser(user);
        } catch (RegisterException e) {
            model.addAttribute("MSG_ERROR",e.getMessage());
            return "register";
        }
        return "redirect:login";
    }
}
