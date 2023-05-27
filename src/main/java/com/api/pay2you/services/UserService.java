package com.api.pay2you.services;

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
			
		User userDocumentExists  = userRepository.findUserByDocument(userDto.getDocument());
		User userEmailExists  = userRepository.findUserByEmail(userDto.getEmail());
		
		if(userDocumentExists != null || userEmailExists != null) {
			throw new UserAlreadyExists("User's document or email already exists");
		}
		
		User user = new User(
				null,
				userDto.getDocument(),
				userDto.generateUserKey(),
				userDto.generateUserSecret(),
				userDto.getEmail(),
				userDto.getName(),
				userDto.getRecovery_pass_token(),
				userDto.getCreated_at()				
				);
		
		user =  userRepository.save(user);
		user.setUser_secret(userDto.getUser_secret());
		return new UserDTO(user);
	}
		
}
