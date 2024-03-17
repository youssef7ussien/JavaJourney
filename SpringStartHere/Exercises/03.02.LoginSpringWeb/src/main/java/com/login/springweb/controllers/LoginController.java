package com.login.springweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.login.springweb.services.AuthenticationService;
import com.login.springweb.services.OnlineUserService;
import com.login.springweb.services.SessionService;

@Controller
@RequestMapping("/users")
public class LoginController {

    private final AuthenticationService authenticationService;
    private final SessionService sessionService;
    private final OnlineUserService onlineUserService;

    public LoginController(
            AuthenticationService authenticationService,
            SessionService sessionService,
            OnlineUserService onlineUserService) {
        this.authenticationService = authenticationService;
        this.sessionService = sessionService;
        this.onlineUserService = onlineUserService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (authenticationService.authenticate(username, password)) {
            onlineUserService.userLoggedIn();
            return "redirect:/home";
        }

        model.addAttribute("error", "Invalid username or password!");
        return "login.html";
    }

    @GetMapping("/logout")
    public String logout() {
        onlineUserService.userLoggedOut();
        sessionService.clearCurrentUser();
        return "redirect:/home";
    }
}
