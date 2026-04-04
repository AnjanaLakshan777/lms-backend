package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="lesson")
public class LessonEntity {
    @Id
    private Long lessonId;
    private String lessonName;

    // Lesson and module
    @ManyToOne
    @JoinColumn(name="module_id")
    private ModuleEntity module;

    // Lesson and content
    @OneToMany(mappedBy = "lesson")
    private List<ContentEntity> contents;
}
