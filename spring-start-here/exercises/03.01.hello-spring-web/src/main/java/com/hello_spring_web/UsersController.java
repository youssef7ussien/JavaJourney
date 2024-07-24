package com.book_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
    private final UserService userService;

    /*
     * Autowiring to UserService
     */
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String addUser(
            @RequestParam String name,
            @RequestParam(required = false) String color,
            Model model) {
        userService.addUser(new User(name, color));
        
        model.addAttribute("users", userService.findAll());

        return "users.html";
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.findAll());

        return "users.html";
    }

}
