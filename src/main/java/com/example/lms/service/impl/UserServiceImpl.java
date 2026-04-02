package com.example.lms.service.impl;

import com.example.lms.dto.UserDto;
import com.example.lms.entity.UserEntity;
import com.example.lms.exception.DuplicateResourceException;
import com.example.lms.mapper.UserMapper;
import com.example.lms.repository.UserRepository;
import com.example.lms.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }
        UserEntity userEntity = userMapper.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.toDto(savedUser);
    }
}