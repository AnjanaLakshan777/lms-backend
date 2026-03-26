package com.example.lms.entity;

import com.example.lms.dto.Role;
import jakarta.persistence.*;
import lombok.*;

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
    @OneToMany(mappedBy = "instructor")
    private List<CourseEntity> courses;

    // Student and enrollment
    @OneToMany(mappedBy = "student")
    private List<EnrollmentEntity> enrollments;

    // Student and grade
    @OneToMany(mappedBy = "student")
    private List<GradeEntity> grades;

    @OneToMany(mappedBy = "sender")
    private List<MessageEntity> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<MessageEntity> receivedMessages;
}
