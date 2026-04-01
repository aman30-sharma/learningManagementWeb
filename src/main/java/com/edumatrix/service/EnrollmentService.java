package com.edumatrix.service;

import com.edumatrix.model.Enrollment;
import com.edumatrix.model.User;
import com.edumatrix.model.Course;
import com.edumatrix.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Enrollment enrollStudent(User student, Course course) {
        if (enrollmentRepository.findByStudentAndCourse(student, course).isPresent()) {
            return null; // Already enrolled
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setProgress(0);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getStudentEnrollments(User student) {
        return enrollmentRepository.findByStudent(student);
    }

    public void updateProgress(Long enrollmentId, Integer progress) {
        enrollmentRepository.findById(enrollmentId).ifPresent(enrollment -> {
            enrollment.setProgress(progress);
            if (progress >= 100) {
                enrollment.setCompletedAt(LocalDateTime.now());
            }
            enrollmentRepository.save(enrollment);
        });
    }
}
