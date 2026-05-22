package com.example.lms.controller;

import com.example.lms.dto.ModuleDto;
import com.example.lms.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/modules")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleDto> saveModule(@RequestBody ModuleDto moduleDto) {
        ModuleDto createdModule = moduleService.saveModule(moduleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModule);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleDto> updateModule(@PathVariable Long id, @RequestBody ModuleDto moduleDto) {
        ModuleDto updatedModule = moduleService.updateModule(id, moduleDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedModule);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ModuleDto> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleDto> getModule(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.findModuleById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModuleDto>> getAllModules() {
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.findAllModules());
    }
}
