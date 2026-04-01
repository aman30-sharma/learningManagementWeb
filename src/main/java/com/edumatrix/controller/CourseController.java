package com.edumatrix.controller;

import com.edumatrix.model.Course;
import com.edumatrix.service.CourseService;
import com.edumatrix.service.UserService;
import com.edumatrix.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public String listCourses(Model model, Authentication auth) {
        try {
            var courses = courseService.getPublishedCourses();
            var user = userService.findByUsername(auth.getName());
            var enrollments = enrollmentService.getStudentEnrollments(user);
            
            model.addAttribute("courses", courses != null ? courses : List.of());
            model.addAttribute("enrollments", enrollments != null ? enrollments : List.of());
            return "course/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to load courses: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String courseDetail(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "course/detail";
    }

    @GetMapping("/create")
    public String createCoursePage(Model model) {
        model.addAttribute("course", new Course());
        return "course/create";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute Course course) {
        courseService.createCourse(course);
        return "redirect:/courses";
    }
}
