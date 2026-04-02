package com.project.resumeanalyzer.controller;

import com.project.resumeanalyzer.Entity.User;
import com.project.resumeanalyzer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        repo.save(user);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existing = repo.findByUsernameAndPassword(
                user.getUsername(),
                user.getPassword()
        );

        if (existing != null) {
            return "Login Successful";
        } else {
            return "Invalid Credentials";
        }
    }
}