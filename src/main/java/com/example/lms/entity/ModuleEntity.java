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
@Table(name="module")
public class ModuleEntity {
    @Id
    private String moduleId;
    private String moduleName;
    private String description;

    // Module and course
    @ManyToOne
    @JoinColumn(name="course_id")
    private CourseEntity course;

    // Module and lesson
    @OneToMany(mappedBy = "module")
    private List<LessonEntity> lessons;
}
