package com.example.lms.service.impl;

import com.example.lms.dto.CourseDto;
import com.example.lms.entity.CourseEntity;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.mapper.CourseMapper;
import com.example.lms.repository.CourseRepository;
import com.example.lms.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {
        CourseEntity courseEntity = courseMapper.toEntity(courseDto);
        CourseEntity savedCourse = courseRepository.save(courseEntity);
        return courseMapper.toDto(savedCourse);
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        CourseEntity existingCourse = courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Course not found"));

        existingCourse.setCourseName(courseDto.getCourseName());
        existingCourse.setDescription(courseDto.getDescription());

        CourseEntity updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toDto(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        CourseEntity existingCourse = courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Course not found"));
        courseRepository.delete(existingCourse);
    }

    @Override
    public CourseDto findCourseById(Long id) {
        CourseEntity existingCourse = courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Course not found"));
        return courseMapper.toDto(existingCourse);
    }

    @Override
    public List<CourseDto> findAllCourses() {
        return courseMapper.toDtoList(courseRepository.findAll());
    }
}

