package com.example.lms.mapper;

import com.example.lms.dto.CourseDto;
import com.example.lms.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "instructorId", target = "instructor.id")
    CourseEntity toEntity(CourseDto courseDto);

    @Mapping(source = "instructor.id", target = "instructorId")
    CourseDto toDto(CourseEntity courseEntity);
    List<CourseDto> toDtoList(List<CourseEntity> courseEntities);
}
