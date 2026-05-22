package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDto {
    private Long lessonId;
    private String lessonCode;
    private String lessonName;
    private Long moduleId;
}
