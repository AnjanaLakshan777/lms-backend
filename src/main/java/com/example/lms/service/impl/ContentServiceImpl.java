package com.example.lms.service.impl;

import com.example.lms.dto.ContentDto;
import com.example.lms.entity.ContentEntity;
import com.example.lms.entity.LessonEntity;
import com.example.lms.exception.DuplicateResourceException;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.mapper.ContentMapper;
import com.example.lms.repository.ContentRepository;
import com.example.lms.repository.LessonRepository;
import com.example.lms.service.ContentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final LessonRepository lessonRepository;

    @Override
    public ContentDto saveContent(String contentCode, String title, String type, MultipartFile fileData, Long lessonId) throws IOException {

        if(contentRepository.existsByContentCode(contentCode)) {
            throw new DuplicateResourceException("Content code already exists");
        }
        if(contentRepository.existsByTitle(title)){
            throw new DuplicateResourceException("Title already exists");
        }

        ContentDto  contentDto = new ContentDto();

        contentDto.setContentCode(contentCode);
        contentDto.setTitle(title);
        contentDto.setType(type);

        // Convert file data into base 64
        byte[] fileDataBytes = fileData.getBytes();
        String fileDataString = Base64.getEncoder().encodeToString(fileDataBytes);
        contentDto.setFileData(fileDataString);

        // Add current data and time to uploadAt
        contentDto.setUploadAt(LocalDateTime.now());

        ContentEntity contentEntity = contentMapper.toEntity(contentDto);

        if(lessonId != null) {
            LessonEntity lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
            contentEntity.setLesson(lesson);
        }
        ContentEntity saved = contentRepository.save(contentEntity);
        return contentMapper.toDto(saved);
    }

    @Override
    public ContentDto updateContent(Long id, String contentCode, String title, String type, MultipartFile fileData, Long lessonId) throws IOException {
        ContentEntity existingContent = contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));

        if (!existingContent.getContentCode().equals(contentCode) &&
                contentRepository.existsByContentCode(contentCode)) {
            throw new DuplicateResourceException("Content code already exists");
        }

        if (!existingContent.getTitle().equals(title) &&
                contentRepository.existsByTitle(title)) {
            throw new DuplicateResourceException("Content name already exists");
        }

        existingContent.setContentCode(contentCode);
        existingContent.setTitle(title);
        existingContent.setType(type);

        // Convert file data into base 64
        byte[] fileDataBytes = fileData.getBytes();
        String fileDataString = Base64.getEncoder().encodeToString(fileDataBytes);
        existingContent.setFileData(fileDataString);

        // Add current data and time to uploadAt
        existingContent.setUploadAt(LocalDateTime.now());

        if (lessonId != null) {
            LessonEntity lesson = lessonRepository.findById(lessonId)
                    .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
            existingContent.setLesson(lesson);
        } else {
            existingContent.setLesson(null);
        }

        ContentEntity updated = contentRepository.save(existingContent);
        return contentMapper.toDto(updated);
    }

    @Override
    public void deleteContent(Long id) {
        ContentEntity existingContent =  contentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Content not found"));
        contentRepository.delete(existingContent);
    }

    @Override
    public ContentDto findContentById(Long id) {
        ContentEntity existingContent =  contentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Content not found"));
        return contentMapper.toDto(existingContent);
    }

    @Override
    public List<ContentDto> findAllContents() {
        return contentMapper.toDtoList(contentRepository.findAll());
    }
}
