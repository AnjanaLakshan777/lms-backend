package com.example.lms.controller;

import com.example.lms.dto.ContentDto;
import com.example.lms.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContentDto> saveContent(@RequestParam String contentCode, @RequestParam String title, @RequestParam String type, @RequestParam MultipartFile fileData, @RequestParam Long lessonId) throws IOException {
        ContentDto createdContent = contentService.saveContent(contentCode,title,type,fileData,lessonId);
        return new ResponseEntity<>(createdContent, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContentDto> updateContent(@PathVariable Long id,@RequestParam String contentCode, @RequestParam String title, @RequestParam String type, @RequestParam MultipartFile fileData, @RequestParam Long lessonId) throws IOException {
        ContentDto updatedContent = contentService.updateContent(id,contentCode,title,type,fileData,lessonId);
        return new ResponseEntity<>(updatedContent, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ContentDto> deleteContent(@PathVariable Long id){
        contentService.deleteContent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContentDto> getContent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(contentService.findContentById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContentDto>> getAllContents(){
        return ResponseEntity.status(HttpStatus.OK).body(contentService.findAllContents());
    }
}
