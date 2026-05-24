package com.example.lms.repository;

import com.example.lms.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentEntity,Long> {
    boolean existsByContentCode(String contentCode);
    boolean existsByTitle(String title);
}