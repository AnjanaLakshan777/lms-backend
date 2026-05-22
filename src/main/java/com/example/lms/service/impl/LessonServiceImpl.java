package com.example.lms.service.impl;

import com.example.lms.dto.LessonDto;
import com.example.lms.entity.LessonEntity;
import com.example.lms.entity.ModuleEntity;
import com.example.lms.exception.DuplicateResourceException;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.mapper.LessonMapper;
import com.example.lms.repository.LessonRepository;
import com.example.lms.repository.ModuleRepository;
import com.example.lms.service.LessonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final ModuleRepository moduleRepository;

    @Override
    public LessonDto saveLesson(LessonDto lessonDto) {

        if (lessonRepository.existsByLessonCode(lessonDto.getLessonCode())) {
            throw new DuplicateResourceException("Lesson code already exists");
        }

        if (lessonRepository.existsByLessonName(lessonDto.getLessonName())) {
            throw new DuplicateResourceException("Module name already exists");
        }

        LessonEntity lessonEntity = lessonMapper.toEntity(lessonDto);

        if (lessonDto.getModuleId() != null) {
            ModuleEntity module = moduleRepository.findById(lessonDto.getModuleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

            lessonEntity.setModule(module);
        }

        LessonEntity saved = lessonRepository.save(lessonEntity);
        return lessonMapper.toDto(saved);
    }

    @Override
    public LessonDto updateLesson(Long id, LessonDto lessonDto) {
        LessonEntity existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));

        if (!existingLesson.getLessonCode().equals(lessonDto.getLessonCode()) &&
                lessonRepository.existsByLessonCode(lessonDto.getLessonCode())) {
            throw new DuplicateResourceException("Lesson code already exists");
        }

        if (!existingLesson.getLessonName().equals(lessonDto.getLessonName()) &&
                lessonRepository.existsByLessonName(lessonDto.getLessonName())) {
            throw new DuplicateResourceException("Lesson name already exists");
        }

        existingLesson.setLessonCode(lessonDto.getLessonCode());
        existingLesson.setLessonName(lessonDto.getLessonName());

        if (lessonDto.getModuleId() != null) {
            ModuleEntity module = moduleRepository.findById(lessonDto.getModuleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Module not found"));
            existingLesson.setModule(module);
        } else {
            existingLesson.setModule(null);
        }

        LessonEntity updated = lessonRepository.save(existingLesson);
        return lessonMapper.toDto(updated);
    }

    @Override
    public void deleteLesson(Long id) {
        LessonEntity existingLesson = lessonRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Lesson not found"));
        lessonRepository.delete(existingLesson);
    }

    @Override
    public LessonDto findLessonById(Long id) {
        LessonEntity existingLesson = lessonRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Lesson not found"));
        return lessonMapper.toDto(existingLesson);
    }

    @Override
    public List<LessonDto> findAllLessons() {
        return lessonMapper.toDtoList(lessonRepository.findAll());
    }
}
