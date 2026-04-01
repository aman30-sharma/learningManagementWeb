package com.edumatrix.controller;

import com.edumatrix.service.AnalyticsService;
import com.edumatrix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String analyticsPage(Authentication auth, Model model) {
        var user = userService.findByUsername(auth.getName());
        model.addAttribute("analytics", analyticsService.getStudentAnalytics(user));
        return "analytics/dashboard";
    }
}
