package com.example.dtodemo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dtodemo.dto.UserRegistrationDTO;
import com.example.dtodemo.dto.UserResponseDTO;
import com.example.dtodemo.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserResponseDTO toResponseDto(UserEntity user);
	
	@Mapping(source = "password", target = "passwordHash")
    @Mapping(target = "id", ignore = true) // Database handles generation
    @Mapping(target = "isInternalAdmin", constant = "false") // Set a default system value
	UserEntity toEntity(UserRegistrationDTO dto);
}
