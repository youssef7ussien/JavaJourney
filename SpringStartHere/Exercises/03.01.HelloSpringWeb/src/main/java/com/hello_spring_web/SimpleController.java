package com.book_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleController {

    /*
     * Simple static view.
     */
    @RequestMapping("/home") // RequestMethod.GET is default
    public String home() {
        return "home.html";
    }

    /*
     * Simple dynamic view with template engine "Thymeleaf".
     */
    @RequestMapping("/get")
    public String getPage(Model page) {
        page.addAttribute("username", "Test");
        page.addAttribute("color", "Red");

        return "get_page.html";
    }

    /*
     * Simple dynamic view with template engine "Thymeleaf"
     * and using request parameters.
     */
    @RequestMapping("/getparam")
    public String getPage(
            @RequestParam String username,
            @RequestParam(required = false) String color,
            Model page) {
        page.addAttribute("username", username);
        page.addAttribute("color", color);

        return "get_page.html";
    }

    /*
     * Simple dynamic view with template engine "Thymeleaf"
     * and using path variable.
     */
    @RequestMapping("/get/{username}/{color}")
    public String getPage1(
            @PathVariable String username,
            @PathVariable String color,
            Model page) {
        page.addAttribute("username", username);
        page.addAttribute("color", color);

        return "get_page.html";
    }

}
