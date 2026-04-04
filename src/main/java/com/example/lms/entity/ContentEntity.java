package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="content")
public class ContentEntity {
    @Id
    private Long contentId;
    private String title;
    private String type;
    private String fileData;
    private String uploadAt;

    // Content and lesson
    @ManyToOne
    @JoinColumn(name="lesson_id")
    private LessonEntity lesson;
}
