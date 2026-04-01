package com.edumatrix.service;

import com.edumatrix.model.Course;
import com.edumatrix.model.User;
import com.edumatrix.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getPublishedCourses() {
        return courseRepository.findByPublishedTrue();
    }

    public List<Course> getCoursesByInstructor(User instructor) {
        return courseRepository.findByInstructor(instructor);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
