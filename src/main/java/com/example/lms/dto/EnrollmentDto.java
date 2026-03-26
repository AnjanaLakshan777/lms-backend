package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentDto {
    private String enrollId;
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private LocalDateTime enrolledAt;
}
