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
@Table(name="grade")
public class GradeEntity {
    @Id
    private String gradeId;
    private String grade;

    // Grade and course
    @ManyToOne
    @JoinColumn(name="course_id")
    private CourseEntity course;

    // Grade and student
    @ManyToOne
    @JoinColumn(name="student_id")
    private UserEntity student;
}
