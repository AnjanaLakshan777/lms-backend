package com.example.lms.service.impl;

import com.example.lms.dto.CourseDto;
import com.example.lms.entity.CourseEntity;
import com.example.lms.entity.UserEntity;
import com.example.lms.exception.DuplicateResourceException;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.mapper.CourseMapper;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {

        if (courseRepository.existsByCourseCode(courseDto.getCourseCode())) {
            throw new DuplicateResourceException("Course code already exists");
        }

        if (courseRepository.existsByCourseName(courseDto.getCourseName())) {
            throw new DuplicateResourceException("Course name already exists");
        }

        CourseEntity courseEntity = courseMapper.toEntity(courseDto);

        if (courseDto.getInstructorId() != null) {
            UserEntity instructor = userRepository.findById(courseDto.getInstructorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

            courseEntity.setInstructor(instructor);
        }

        CourseEntity savedCourse = courseRepository.save(courseEntity);
        return courseMapper.toDto(savedCourse);
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {

        CourseEntity existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (!existingCourse.getCourseCode().equals(courseDto.getCourseCode()) &&
                courseRepository.existsByCourseCode(courseDto.getCourseCode())) {
            throw new DuplicateResourceException("Course code already exists");
        }

        if (!existingCourse.getCourseName().equals(courseDto.getCourseName()) &&
                courseRepository.existsByCourseName(courseDto.getCourseName())) {
            throw new DuplicateResourceException("Course name already exists");
        }

        existingCourse.setCourseCode(courseDto.getCourseCode());
        existingCourse.setCourseName(courseDto.getCourseName());
        existingCourse.setDescription(courseDto.getDescription());

        if (courseDto.getInstructorId() != null) {
            UserEntity instructor = userRepository.findById(courseDto.getInstructorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

            existingCourse.setInstructor(instructor);
        } else {
            existingCourse.setInstructor(null);
        }

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

