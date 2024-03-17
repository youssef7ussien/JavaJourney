package com.login.springweb.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.login.springweb.models.User;
import com.login.springweb.services.OnlineUserService;
import com.login.springweb.services.SessionService;
import com.login.springweb.services.UserService;

@Controller
@RequestMapping("/")
public class MainController {
    private final UserService userService;
    private final SessionService sessionService;
    private final OnlineUserService onlineUserService;

    public MainController(
            UserService userService,
            SessionService sessionService,
            OnlineUserService onlineUserCountService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.onlineUserService = onlineUserCountService;
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("home")
    public String homePage(Model model) {
        model.addAttribute("onlineUsersCount", onlineUserService.currentOnlineUsers());

        if (sessionService.isUserLoggedIn()) {
            User user = sessionService.getCurrentUser();
            model.addAttribute("username", user.getUsername());
            model.addAttribute("color", user.getColor());
        }

        return "home.html";
    }

    @GetMapping("users/list")
    public String showUserList(Model model) {
        if (!sessionService.isUserLoggedIn())
            return "redirect:/users/login";

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users.html";
    }
}
