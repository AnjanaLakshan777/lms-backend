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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "module_seq_gen")
    @SequenceGenerator(name = "module_seq_gen", sequenceName = "module_seq", allocationSize = 1)
    private Long moduleId;
    private String moduleCode;
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
