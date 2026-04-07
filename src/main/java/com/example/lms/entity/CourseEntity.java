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
@Table(name="course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq_gen")
    @SequenceGenerator(name = "course_seq_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long courseId;
    private String courseName;
    private String description;

    // Course and instructor
    @ManyToOne
    @JoinColumn(name="instructor_id")
    private UserEntity instructor;

    // Course and enrollment
    @OneToMany(mappedBy = "course")
    private List<EnrollmentEntity> enrollments;

    // Course and grade
    @OneToMany(mappedBy = "course")
    private List<GradeEntity> grades;

    // Course and module
    @OneToMany(mappedBy = "course")
    private List<ModuleEntity> modules;
}
