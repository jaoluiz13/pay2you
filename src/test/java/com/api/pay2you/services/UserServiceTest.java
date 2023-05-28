package com.api.pay2you.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.pay2you.dtos.UserDTO;
import com.api.pay2you.entities.User;
import com.api.pay2you.exceptions.user.UserAlreadyExists;
import com.api.pay2you.repositories.UserRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createUserTest() {

		UserDTO userDto = new UserDTO("01000000000", "test@gmail.com", "User Test", "");

		User expectedUser = new User("01000000000", "test@gmail.com", "User Test", "");

		when(userRepository.save(any(User.class))).thenReturn(expectedUser);

		UserDTO createdUser = userService.createUser(userDto);

		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		verify(userRepository, times(1)).save(userCaptor.capture());

		User resultado = userCaptor.getValue();

		assertEquals(expectedUser.getDocument(), resultado.getDocument());
		assertEquals(expectedUser.getEmail(), resultado.getEmail());
		assertEquals(expectedUser.getName(), resultado.getName());
	}

	@Test()
	void cannotCreateAUserWithAEmailorDocumentAlreadyExists() {

		UserDTO userDto = new UserDTO("01000000000", "existing-email@test.com", "User Test", "");

		when(userRepository.findUserByEmail(userDto.getEmail())).thenReturn(Optional.of(new User()));
		when(userRepository.findUserByDocument(userDto.getDocument())).thenReturn(Optional.of(new User()));

		UserAlreadyExists exception = assertThrows(UserAlreadyExists.class, () -> userService.createUser(userDto));

		assertEquals("User's document or email already exists", exception.getMessage());
	}

}
