package com.edumatrix.controller;

import com.edumatrix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(Authentication auth, Model model) {
        try {
            var user = userService.findByUsername(auth.getName());
            if (user == null) {
                return "redirect:/login";
            }
            model.addAttribute("user", user);
            return "profile/view";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to load profile: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/delete")
    public String deleteAccount(Authentication auth) {
        try {
            var user = userService.findByUsername(auth.getName());
            if (user != null) {
                userService.deleteUser(user.getId());
            }
            return "redirect:/logout";
        } catch (Exception e) {
            return "redirect:/profile?error=delete_failed";
        }
    }
}
