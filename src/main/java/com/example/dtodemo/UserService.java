package com.example.dtodemo;


import org.springframework.stereotype.Service;

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
		UserResponseDTO userResponseDTO  = userMapper.toDto(userEntity);
		return userResponseDTO;
	}

}
