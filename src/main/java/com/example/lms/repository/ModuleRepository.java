package com.example.lms.repository;

import com.example.lms.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<ModuleEntity,Long> {
    boolean existsByModuleCode(String moduleCode);
    boolean existsByModuleName(String moduleName);
}
