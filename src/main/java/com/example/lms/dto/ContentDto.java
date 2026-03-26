package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentDto {
    private String contentId;
    private String title;
    private String type;
    private String fileData;
    private String uploadAt;
}
