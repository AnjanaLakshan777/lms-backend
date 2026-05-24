package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentDto {
    private Long contentId;
    private String contentCode;
    private String title;
    private String type;
    private String fileData;
    private LocalDateTime uploadAt;
    private Long lessonId;
}
