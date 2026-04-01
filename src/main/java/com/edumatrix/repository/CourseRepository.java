package com.edumatrix.repository;

import com.edumatrix.model.Course;
import com.edumatrix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructor(User instructor);
    List<Course> findByPublishedTrue();
    List<Course> findByCategory(String category);
}
