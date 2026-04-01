package com.edumatrix.repository;

import com.edumatrix.model.Performance;
import com.edumatrix.model.User;
import com.edumatrix.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findByStudent(User student);
    List<Performance> findByCourse(Course course);
    List<Performance> findByStudentAndCourse(User student, Course course);
}
