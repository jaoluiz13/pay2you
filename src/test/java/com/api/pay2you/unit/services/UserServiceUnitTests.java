package com.api.pay2you.unit.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.api.pay2you.dtos.UserDTO;
import com.api.pay2you.entities.User;
import com.api.pay2you.repositories.UserRepository;
import com.api.pay2you.services.UserService;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceUnitTests {

	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
	@Test
	public void createUser() {
		UserDTO userDtoTest = new UserDTO(null,"01000000000","test@gmail.com","User Test","");
		User userTest = new User(null,"01000000000","test@gmail.com","User Test","");
		
		//Mock do UserRepository
        when(userRepository.save(userTest)).thenReturn(userTest);
        
        //Chamar o método de cadastro de usuário
        UserDTO resultado = userService.createUser(userDtoTest);

        //Verificar se o método save foi chamado no UserRepository
        verify(userRepository).save(userTest);

        //Verificar se o resultado é o esperado
        assertEquals(userDtoTest.getDocument(),resultado.getDocument());
        assertEquals(userDtoTest.getEmail(),resultado.getEmail());
	}

	@Test
	public void notCreateUsersWithSameDocumentOrEmail() {

	}
}
