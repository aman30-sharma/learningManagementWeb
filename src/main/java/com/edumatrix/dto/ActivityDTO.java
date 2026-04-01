package com.edumatrix.dto;

import lombok.Data;

@Data
public class ActivityDTO {
    private String activityType;
    private String details;
    private String timestamp;
    private Long courseId;
}
