package com.example.lms.mapper;

import com.example.lms.dto.UserDto;
import com.example.lms.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserDto userDto);
    @Mapping(target="password", ignore = true)
    UserDto toDto(UserEntity userEntity);
}
