package com.edumatrix.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "performance")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    private Integer quizScore;
    private Integer assignmentScore;
    private Integer participationScore;
    private Double overallGrade;
    
    @Column(nullable = false)
    private LocalDateTime recordedAt = LocalDateTime.now();
    
    private Integer timeSpent; // in minutes
    private Integer modulesCompleted;
}
