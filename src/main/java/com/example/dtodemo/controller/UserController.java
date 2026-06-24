package com.example.dtodemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtodemo.dto.UserRegistrationDTO;
import com.example.dtodemo.dto.UserResponseDTO;
import com.example.dtodemo.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRegistrationDTO user){
		UserResponseDTO response =   userService.createUser(user);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	

	@GetMapping("/{id}")
	ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
		UserResponseDTO reponse = userService.getUserById(id);
		return ResponseEntity.ok(reponse);
	}
	
	

}
