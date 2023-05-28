package com.api.pay2you.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.pay2you.dtos.UserDTO;
import com.api.pay2you.entities.User;
import com.api.pay2you.exceptions.user.UserAlreadyExists;
import com.api.pay2you.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDTO createUser(UserDTO userDto) {
			
		Optional<User> userDocumentExists  = userRepository.findUserByDocument(userDto.getDocument());
		Optional<User> userEmailExists  = userRepository.findUserByEmail(userDto.getEmail());
		
		if(userDocumentExists.isPresent() || userEmailExists.isPresent()) {
			throw new UserAlreadyExists("User's document or email already exists");
		}
		
		User user = new User(
				userDto.getDocument(),
				userDto.getEmail(),
				userDto.getName(),
				userDto.getRecovery_pass_token()		
				);
		
		user =  userRepository.save(user);
		user.setUser_secret(user.getUser_secret_plain_text());
		return new UserDTO(user);
	}
		
}
