package com.example.lms.mapper;

import com.example.lms.dto.CourseDto;
import com.example.lms.entity.CourseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseEntity toEntity(CourseDto courseDto);
    CourseDto toDto(CourseEntity courseEntity);
    List<CourseDto> toDtoList(List<CourseEntity> courseEntities);
}
