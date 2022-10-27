package com.example.springsecurityconfigwithoutadapter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    /**
     * This endpoint must be accessible for everyone
     */
    @GetMapping
    public String home() {
        return "Hello, World";
    }

    /**
     * This endpoint must only be available for users authenticated, no matter the role
     */
    @GetMapping("/user")
    public String user() {
        return "Hello, user";
    }


    /**
     * We want this endpoint to only be available for users  authenticated with the ADMIN role
     */
    @GetMapping("/admin")
    public String admin() {
        return "Hello, admin";
    }
}
