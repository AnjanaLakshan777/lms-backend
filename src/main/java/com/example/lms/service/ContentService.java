package com.example.lms.service;

import com.example.lms.dto.ContentDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ContentService {
    ContentDto saveContent(String contentCode, String title, String type, MultipartFile fileData, Long lessonId) throws IOException;
    ContentDto updateContent(Long id, String contentCode, String title, String type, MultipartFile fileData, Long lessonId) throws IOException;
    void deleteContent(Long id);
    ContentDto findContentById(Long id);
    List<ContentDto> findAllContents();
}