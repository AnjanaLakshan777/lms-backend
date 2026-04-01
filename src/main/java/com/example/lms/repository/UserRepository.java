package com.example.lms.repository;

import com.example.lms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    boolean existsByEmail(String email);
}
