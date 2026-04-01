package com.edumatrix.dto;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long studentId;
    private Long courseId;
    private Integer progress;
    private Double grade;
}
