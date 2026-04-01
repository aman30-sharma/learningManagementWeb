package com.edumatrix.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @Column(nullable = false)
    private String activityType; // LOGIN, COURSE_ACCESS, MODULE_VIEW, QUIZ_ATTEMPT
    
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
    
    private Integer duration; // in seconds
    private String details;
}
