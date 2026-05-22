package com.example.lms.repository;

import com.example.lms.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
    boolean existsByLessonCode(String lessonCode);
    boolean existsByLessonName(String lessonName);
}
