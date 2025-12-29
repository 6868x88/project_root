package com.example.newsapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.newsapi.entity.User;
import com.example.newsapi.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            HttpSession session
    ) {
        User user = userRepository
            .findByEmail(email)
            .orElseGet(() -> userRepository.save(new User(email)));

        session.setAttribute("user", user);

        // ✅ 핵심
        return "redirect:/";
    }
}
