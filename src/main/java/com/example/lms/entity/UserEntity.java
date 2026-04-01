package com.example.lms.entity;

import com.example.lms.dto.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    // Instructor and coruse
    @OneToMany(mappedBy = "instructor", fetch =  FetchType.LAZY)
    private List<CourseEntity> courses = new ArrayList<>();

    // Student and enrollment
    @OneToMany(mappedBy = "student", fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    // Student and grade
    @OneToMany(mappedBy = "student", fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GradeEntity> grades = new ArrayList<>();

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<MessageEntity> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<MessageEntity> receivedMessages = new ArrayList<>();
}
