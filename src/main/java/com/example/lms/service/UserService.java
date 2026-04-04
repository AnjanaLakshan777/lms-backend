package com.example.lms.service;

import com.example.lms.dto.UserDto;

import java.util.List;


public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
}
