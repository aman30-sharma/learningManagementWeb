package com.edumatrix.controller;

import com.edumatrix.model.User;
import com.edumatrix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists");
            return "auth/register";
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email is already registered");
            return "auth/register";
        }
        userService.registerUser(user);
        return "redirect:/login?registered";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        try {
            if (auth == null || auth.getName() == null) {
                return "redirect:/login";
            }
            
            var user = userService.findByUsername(auth.getName());
            if (user == null) {
                return "redirect:/login";
            }
            
            var enrollments = userService.getStudentEnrollments(user);
            
            model.addAttribute("user", user);
            model.addAttribute("enrollments", enrollments != null ? enrollments : List.of());
            model.addAttribute("enrollmentCount", enrollments != null ? enrollments.size() : 0);
            
            return "dashboard/student";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to load dashboard: " + e.getMessage());
            return "error";
        }
    }
}
