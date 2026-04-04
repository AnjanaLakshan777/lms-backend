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
