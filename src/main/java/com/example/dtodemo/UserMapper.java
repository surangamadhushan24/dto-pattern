package com.example.dtodemo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserResponseDTO toDto(UserEntity user);
}
