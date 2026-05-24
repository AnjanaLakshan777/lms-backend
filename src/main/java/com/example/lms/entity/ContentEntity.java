package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="content")
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_seq_gen")
    @SequenceGenerator(name = "content_seq_gen", sequenceName = "content_seq", allocationSize = 1)
    private Long contentId;
    private String contentCode;
    private String title;
    private String type;
    @Lob
    private String fileData;
    private LocalDateTime uploadAt;

    // Content and lesson
    @ManyToOne
    @JoinColumn(name="lesson_id")
    private LessonEntity lesson;
}
