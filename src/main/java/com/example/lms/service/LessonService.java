package com.example.lms.service;

import com.example.lms.dto.LessonDto;

import java.util.List;

public interface LessonService {
    LessonDto saveLesson(LessonDto lessonDto);
    LessonDto updateLesson(Long id, LessonDto lessonDto);
    void deleteLesson(Long id);
    LessonDto findLessonById(Long id);
    List<LessonDto> findAllLessons();
}
