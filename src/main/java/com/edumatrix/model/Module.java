package com.edumatrix.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    private Integer orderIndex;
    private String videoUrl;
    private String documentUrl;
}
