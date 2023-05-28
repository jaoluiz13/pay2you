package com.api.pay2you.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.pay2you.entities.User;
import com.api.pay2you.repositories.UserRepository;


@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

	
	@Mock
     UserRepository userRepository;

    @InjectMocks
     UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
	@Test
	@DisplayName("Should Be Able To Create a User")
	void createUser() {
		//UserDTO userDtoTest = new UserDTO(null,"010000800000","test@gmail.com","User Test","");
		User userTest = new User(null,"01000000002","test4@gmail.com","User Test","");
		
		
        when(userRepository.save(userTest)).thenReturn(userTest);
        verify(userRepository).save(userTest);
        
        User resultado = userRepository.save(userTest);
        
        resultado.setDocument("01000000001");
        assertEquals(userTest.getDocument(),resultado.getDocument());
        assertEquals(userTest.getEmail(),resultado.getEmail());
	}
}
