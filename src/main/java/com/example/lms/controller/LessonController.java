package com.example.lms.controller;

import com.example.lms.dto.LessonDto;
import com.example.lms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDto> saveLesson(@RequestBody LessonDto lessonDto) {
        LessonDto createdLesson = lessonService.saveLesson(lessonDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDto> updateLesson(@PathVariable Long id, @RequestBody LessonDto lessonDto) {
        LessonDto updatedLesson = lessonService.updateLesson(id, lessonDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedLesson);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<LessonDto> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDto> getLesson(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findLessonById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LessonDto>> getAllLessons() {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllLessons());
    }
}
