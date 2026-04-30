package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq_gen")
    @SequenceGenerator(name = "course_seq_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long courseId;
    private String courseCode;
    private String courseName;
    private String description;

    // Course and instructor
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="instructor_id")
    private UserEntity instructor;

    // Course and enrollment
    @OneToMany(mappedBy = "course", fetch =  FetchType.LAZY)
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    // Course and grade
    @OneToMany(mappedBy = "course", fetch =  FetchType.LAZY)
    private List<GradeEntity> grades = new ArrayList<>();

    // Course and module
    @OneToMany(mappedBy = "course", fetch =  FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModuleEntity> modules = new ArrayList<>();
}
