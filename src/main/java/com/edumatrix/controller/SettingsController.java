package com.edumatrix.controller;

import com.edumatrix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String settings(Authentication auth, Model model) {
        try {
            var user = userService.findByUsername(auth.getName());
            if (user == null) {
                return "redirect:/login";
            }
            model.addAttribute("user", user);
            return "settings/view";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to load settings: " + e.getMessage());
            return "error";
        }
    }
}
