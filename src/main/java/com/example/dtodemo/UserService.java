package com.example.dtodemo;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	public UserResponseDTO getUserById(Long id) {
		UserEntity userEntity =  userRepository.findById(id)
				.orElseThrow(()->new RuntimeException("user not found"));
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setId(userEntity.getId());
		userResponseDTO.setName(userEntity.getName());
		userResponseDTO.setEmail(userEntity.getEmail());
	
		return userResponseDTO;
	}

}
