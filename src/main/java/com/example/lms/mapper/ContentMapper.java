package com.example.lms.mapper;

import com.example.lms.dto.ContentDto;
import com.example.lms.entity.ContentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    @Mapping(source = "lessonId", target = "lesson.lessonId")
    ContentEntity toEntity(ContentDto contentDto);

    @Mapping(source = "lesson.lessonId", target = "lessonId")
    @Mapping(target = "fileData",  ignore = true)
    ContentDto toDto(ContentEntity contentEntity);
    List<ContentDto> toDtoList(List<ContentEntity> contentEntityList);
}