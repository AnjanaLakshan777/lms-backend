package com.example.lms.service.impl;

import com.example.lms.dto.UserDto;
import com.example.lms.entity.UserEntity;
import com.example.lms.exception.DuplicateResourceException;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.mapper.UserMapper;
import com.example.lms.repository.UserRepository;
import com.example.lms.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


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

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        if(!existingUser.getEmail().equals(userDto.getEmail()) && userRepository.existsByEmail(userDto.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());
        existingUser.setAddressLine1(userDto.getAddressLine1());
        existingUser.setAddressLine2(userDto.getAddressLine2());
        existingUser.setAddressLine3(userDto.getAddressLine3());
        existingUser.setCity(userDto.getCity());
        existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        UserEntity updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        userRepository.delete(existingUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        return userMapper.toDto(existingUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }
}