package com.example.lms.controller;

import com.example.lms.dto.CourseDto;
import com.example.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> saveCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.saveCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @PutMapping(value = "{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id,@RequestBody CourseDto courseDto) {
        CourseDto updatedCourse = courseService.updateCourse(id, courseDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findCourseById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAllCourses());
    }
}
