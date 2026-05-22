package com.example.lms.service;

import com.example.lms.dto.ModuleDto;

import java.util.List;

public interface ModuleService {
    ModuleDto saveModule(ModuleDto moduleDto);
    ModuleDto updateModule(Long id, ModuleDto moduleDto);
    void deleteModule(Long id);
    ModuleDto findModuleById(Long id);
    List<ModuleDto> findAllModules();
}
