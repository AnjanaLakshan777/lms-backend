package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="enrollment")
public class EnrollmentEntity {
    @Id
    private String enrollId;

    // Enrollment and student
    @ManyToOne
    @JoinColumn(name = "student_id")
    private UserEntity student;

    // Enrollment and course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    private LocalDateTime enrolledAt;
}
