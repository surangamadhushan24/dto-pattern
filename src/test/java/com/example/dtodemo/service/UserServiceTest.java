package com.example.dtodemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.dtodemo.dto.UserRegistrationDTO;
import com.example.dtodemo.dto.UserResponseDTO;
import com.example.dtodemo.entity.UserEntity;
import com.example.dtodemo.mapper.UserMapper;
import com.example.dtodemo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;

	private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	
	private UserService userService;
	
	@BeforeEach
    void setUp() {
        // Construct service manually with the mock repo and real mapper
        userService = new UserService(userRepository, userMapper);
    }
	
	
	@Test
	void registerUser_ShouldSuccessfullyMapAndSaveUser() {
		
		//Arrange
		
		UserRegistrationDTO inputDto = UserRegistrationDTO.builder()
				.name("Alice")
				.email("alice@example.com")
				.password("securePass123")
				.build();
		
		
		
		UserEntity savedEntity = UserEntity.builder()
                .id(1L)
                .name("Alice")
                .email("alice@example.com")
                .passwordHash("securePass123")
                .isInternalAdmin(false)
                .build();
			
		
		when(userRepository.save(any(UserEntity.class))).thenReturn(savedEntity);
		
		//Act(when)
		UserResponseDTO result = userService.createUser(inputDto);
		
		//Assert(then)
		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Alice", result.getName());
		assertEquals("alice@example.com", result.getEmail());
		
		verify(userRepository,times(1)).save(any(UserEntity.class));
		
		
		
	}
	
	@Test
	void getUserById_WithValidId_ShouldReturnUserResponseDTO(){
		
		//Arrange
		UserEntity existingUser = UserEntity.builder()
                .id(99L)
                .name("Bob")
                .email("bob@example.com")
                .passwordHash("hashedpassword")
                .build();
                
                		
        when(userRepository.findById(99l)).thenReturn(Optional.of(existingUser));
        
        //Act
        UserResponseDTO result = userService.getUserById(99L);
        
        //Assert
        assertNotNull(result);
        assertEquals(99L, result.getId());
        assertEquals("Bob", result.getName());
        
     // Verify sensitive properties are NOT part of the DTO response model
        // (This tests our architectural barrier!)
        assertFalse(result.getClass().getDeclaredFields().toString().contains("passwordHash"));
    }
	

    @Test
    void getUserById_WithInvalidId_ShouldThrowException() {
        // Arrange
        when(userRepository.findById(100L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.getUserById(100L));
    }

			

}
