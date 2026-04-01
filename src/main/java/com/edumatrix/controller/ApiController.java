package com.edumatrix.controller;

import com.edumatrix.dto.ActivityDTO;
import com.edumatrix.dto.EnrollmentDTO;
import com.edumatrix.service.ActivityTrackingService;
import com.edumatrix.service.EnrollmentService;
import com.edumatrix.service.UserService;
import com.edumatrix.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ActivityTrackingService activityTrackingService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/activity/track")
    public ResponseEntity<Map<String, String>> trackActivity(
            @RequestBody ActivityDTO activityDTO,
            Authentication auth) {
        try {
            var user = userService.findByUsername(auth.getName());
            var course = activityDTO.getCourseId() != null 
                ? courseService.getCourseById(activityDTO.getCourseId()) 
                : null;
            
            activityTrackingService.trackActivity(
                user, 
                course, 
                activityDTO.getActivityType(), 
                activityDTO.getDetails()
            );
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/enroll")
    public ResponseEntity<Map<String, Object>> enrollCourse(
            @RequestBody EnrollmentDTO enrollmentDTO,
            Authentication auth) {
        try {
            var user = userService.findByUsername(auth.getName());
            var course = courseService.getCourseById(enrollmentDTO.getCourseId());
            
            var enrollment = enrollmentService.enrollStudent(user, course);
            
            Map<String, Object> response = new HashMap<>();
            if (enrollment != null) {
                response.put("status", "success");
                response.put("message", "Successfully enrolled");
            } else {
                response.put("status", "error");
                response.put("message", "Already enrolled");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
