package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.entities.Users;
import com.example.app.services.UserService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        Users users = userService.findUserByUsername(username);

        if (users != null && users.getPassword().equals(password)) {
            userService.sendOtp(users);
            return "verify-otp";
        }

        model.addAttribute("error", "Invalid credentials!");
        return "login";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String otp, Model model) {

        if (userService.verifyOtp(otp)) {
            model.addAttribute("message", "OTP verified successfully!");
            return "dashboard";
        }

        model.addAttribute("error", "Invalid or expired OTP!");
        return "verify-otp";
    }
}
