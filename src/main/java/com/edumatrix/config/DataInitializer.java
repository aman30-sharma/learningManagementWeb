package com.edumatrix.config;

import com.edumatrix.model.Course;
import com.edumatrix.model.User;
import com.edumatrix.repository.CourseRepository;
import com.edumatrix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create sample instructor if not exists
        if (userRepository.findByUsername("instructor").isEmpty()) {
            User instructor = new User();
            instructor.setUsername("instructor");
            instructor.setPassword(passwordEncoder.encode("password"));
            instructor.setFullName("John Instructor");
            instructor.setEmail("instructor@edumatrix.com");
            instructor.setRole("INSTRUCTOR");
            instructor.setCreatedAt(LocalDateTime.now());
            instructor.setActive(true);
            userRepository.save(instructor);

            // Create sample courses
            if (courseRepository.count() == 0) {
                Course course1 = new Course();
                course1.setTitle("Web Development Fundamentals");
                course1.setDescription("Learn HTML, CSS, JavaScript and build modern responsive websites from scratch.");
                course1.setCategory("Programming");
                course1.setDuration(40);
                course1.setInstructor(instructor);
                course1.setPublished(true);
                course1.setCreatedAt(LocalDateTime.now());
                courseRepository.save(course1);

                Course course2 = new Course();
                course2.setTitle("Java Programming Masterclass");
                course2.setDescription("Master Java programming from basics to advanced concepts including OOP and design patterns.");
                course2.setCategory("Programming");
                course2.setDuration(60);
                course2.setInstructor(instructor);
                course2.setPublished(true);
                course2.setCreatedAt(LocalDateTime.now());
                courseRepository.save(course2);

                Course course3 = new Course();
                course3.setTitle("Python for Data Science");
                course3.setDescription("Learn Python programming and data analysis with pandas, numpy, and visualization libraries.");
                course3.setCategory("Data Science");
                course3.setDuration(50);
                course3.setInstructor(instructor);
                course3.setPublished(true);
                course3.setCreatedAt(LocalDateTime.now());
                courseRepository.save(course3);
            }
        }
    }
}
