package com.example.dtodemo.service;


import org.springframework.stereotype.Service;

import com.example.dtodemo.dto.UserRegistrationDTO;
import com.example.dtodemo.dto.UserResponseDTO;
import com.example.dtodemo.entity.UserEntity;
import com.example.dtodemo.mapper.UserMapper;
import com.example.dtodemo.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}



	public UserResponseDTO getUserById(Long id) {
		UserEntity userEntity =  userRepository.findById(id)
				.orElseThrow(()->new RuntimeException("user not found"));
		UserResponseDTO userResponseDTO  = userMapper.toResponseDto(userEntity);
		return userResponseDTO;
	}



	public UserResponseDTO createUser(UserRegistrationDTO user) {
		UserEntity userEntity =  userMapper.toEntity(user);
		UserEntity savedUser = userRepository.save(userEntity);
		return userMapper.toResponseDto(savedUser);
	
	}
		

}
	
