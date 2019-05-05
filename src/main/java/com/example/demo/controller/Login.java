package com.example.demo.controller;


import com.example.demo.entity.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

    public class Login {

    @GetMapping("/")
    public String login() {
        return "Login";

    }
}
