package com.example.lms.service.impl;

import com.example.lms.dto.ModuleDto;
import com.example.lms.entity.CourseEntity;
import com.example.lms.entity.ModuleEntity;
import com.example.lms.exception.DuplicateResourceException;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.mapper.ModuleMapper;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.ModuleRepository;
import com.example.lms.service.ModuleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;
    private final CourseRepository courseRepository;

    @Override
    public ModuleDto saveModule(ModuleDto moduleDto) {

        if (moduleRepository.existsByModuleCode(moduleDto.getModuleCode())) {
            throw new DuplicateResourceException("Module code already exists");
        }

        if (moduleRepository.existsByModuleName(moduleDto.getModuleName())) {
            throw new DuplicateResourceException("Module name already exists");
        }

        ModuleEntity moduleEntity = moduleMapper.toEntity(moduleDto);

        if (moduleDto.getCourseId() != null) {
            CourseEntity course = courseRepository.findById(moduleDto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

            moduleEntity.setCourse(course);
        }

        ModuleEntity saved = moduleRepository.save(moduleEntity);
        return moduleMapper.toDto(saved);
    }

    @Override
    public ModuleDto updateModule(Long id, ModuleDto moduleDto) {

        ModuleEntity existingModule = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

        if (!existingModule.getModuleCode().equals(moduleDto.getModuleCode()) &&
                moduleRepository.existsByModuleCode(moduleDto.getModuleCode())) {
            throw new DuplicateResourceException("Module code already exists");
        }

        if (!existingModule.getModuleName().equals(moduleDto.getModuleName()) &&
                moduleRepository.existsByModuleName(moduleDto.getModuleName())) {
            throw new DuplicateResourceException("Module name already exists");
        }

        existingModule.setModuleCode(moduleDto.getModuleCode());
        existingModule.setModuleName(moduleDto.getModuleName());
        existingModule.setDescription(moduleDto.getDescription());

        if (moduleDto.getCourseId() != null) {
            CourseEntity course = courseRepository.findById(moduleDto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            existingModule.setCourse(course);
        } else {
            existingModule.setCourse(null);
        }

        ModuleEntity updated = moduleRepository.save(existingModule);
        return moduleMapper.toDto(updated);
    }

    @Override
    public void deleteModule(Long id) {
        ModuleEntity existingModule = moduleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Module not found"));
        moduleRepository.delete(existingModule);
    }

    @Override
    public ModuleDto findModuleById(Long id) {
        ModuleEntity existingModule = moduleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Module not found"));
        return moduleMapper.toDto(existingModule);
    }

    @Override
    public List<ModuleDto> findAllModules() {
        return moduleMapper.toDtoList(moduleRepository.findAll());
    }
}
