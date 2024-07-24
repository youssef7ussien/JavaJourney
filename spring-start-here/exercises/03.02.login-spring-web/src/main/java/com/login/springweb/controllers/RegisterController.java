package com.login.springweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.login.springweb.models.User;
import com.login.springweb.services.UserService;

@Controller
@RequestMapping("/users")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register.html";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        if (userService.getUserByUsername(user.getUsername()).isEmpty()) {
            userService.saveUser(user);
            return "redirect:/users/login";
        }

        model.addAttribute("error", "Username already exists");
        return "register.html";
    }
}
