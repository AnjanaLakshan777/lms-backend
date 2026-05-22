package com.example.lms.mapper;

import com.example.lms.dto.ModuleDto;
import com.example.lms.entity.ModuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    @Mapping(source = "courseId", target = "course.courseId")
    ModuleEntity toEntity(ModuleDto moduleDto);

    @Mapping(source = "course.courseId", target = "courseId")
    ModuleDto toDto(ModuleEntity moduleEntity);
    List<ModuleDto> toDtoList(List<ModuleEntity> moduleEntities);
}
