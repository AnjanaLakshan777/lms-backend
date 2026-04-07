package com.example.lms.service;

import com.example.lms.dto.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto saveCourse(CourseDto courseDto);
    CourseDto updateCourse(Long id, CourseDto courseDto);
    void deleteCourse(Long id);
    CourseDto findCourseById(Long id);
    List<CourseDto> findAllCourses();
}
