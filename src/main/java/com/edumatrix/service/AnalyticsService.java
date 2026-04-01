package com.edumatrix.service;

import com.edumatrix.model.Performance;
import com.edumatrix.model.User;
import com.edumatrix.model.Course;
import com.edumatrix.repository.PerformanceRepository;
import com.edumatrix.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class AnalyticsService {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Map<String, Object> getStudentAnalytics(User student) {
        Map<String, Object> analytics = new HashMap<>();
        List<Performance> performances = performanceRepository.findByStudent(student);
        
        double avgGrade = performances.stream()
                .mapToDouble(p -> p.getOverallGrade() != null ? p.getOverallGrade() : 0)
                .average()
                .orElse(0.0);
        
        int totalTimeSpent = performances.stream()
                .mapToInt(p -> p.getTimeSpent() != null ? p.getTimeSpent() : 0)
                .sum();
        
        analytics.put("averageGrade", avgGrade);
        analytics.put("totalTimeSpent", totalTimeSpent);
        analytics.put("coursesEnrolled", enrollmentRepository.findByStudent(student).size());
        
        return analytics;
    }

    public Map<String, Object> getCourseAnalytics(Course course) {
        Map<String, Object> analytics = new HashMap<>();
        List<Performance> performances = performanceRepository.findByCourse(course);
        
        double avgScore = performances.stream()
                .mapToDouble(p -> p.getOverallGrade() != null ? p.getOverallGrade() : 0)
                .average()
                .orElse(0.0);
        
        analytics.put("averageScore", avgScore);
        analytics.put("totalStudents", enrollmentRepository.findByCourse(course).size());
        
        return analytics;
    }
}
