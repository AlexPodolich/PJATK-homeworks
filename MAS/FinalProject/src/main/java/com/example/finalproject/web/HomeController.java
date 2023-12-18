package com.example.finalproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Redirect user to reservation menu page
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/reservation/menu";
    }

}
