package com.example.lms.mapper;

import com.example.lms.dto.LessonDto;
import com.example.lms.entity.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    @Mapping(source = "moduleId", target = "module.moduleId")
    LessonEntity toEntity(LessonDto lessonDto);

    @Mapping(source = "module.moduleId", target = "moduleId")
    LessonDto toDto(LessonEntity lessonEntity);
    List<LessonDto> toDtoList(List<LessonEntity> lessonEntities);
}