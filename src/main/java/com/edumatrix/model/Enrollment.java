package com.edumatrix.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @Column(nullable = false)
    private LocalDateTime enrolledAt = LocalDateTime.now();
    
    private Integer progress = 0; // 0-100
    private LocalDateTime completedAt;
    private Double grade;
}
