package com.api.pay2you.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.pay2you.dtos.JwtDTO;
import com.api.pay2you.dtos.LoginDTO;
import com.api.pay2you.dtos.UserDTO;
import com.api.pay2you.entities.User;
import com.api.pay2you.exceptions.user.InvalidCredentials;
import com.api.pay2you.exceptions.user.UserAlreadyExists;
import com.api.pay2you.exceptions.user.UserNotFound;
import com.api.pay2you.repositories.UserRepository;
import com.api.pay2you.utils.Jwt;
import com.api.pay2you.utils.Password;

@Service
public class UserService{

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
	
	public JwtDTO login(LoginDTO login) {
		
		Optional<User> userExists = userRepository.findUserByKey(login.getUser_key());
		
		if(userExists.isEmpty()) {
			throw new InvalidCredentials("Incorrect user key or secret!");
		}
		
		Boolean passwordIsValid = Password.PasswordMatcher(login.getUser_secret(), userExists.get().getUser_secret());
		
		if(!passwordIsValid) {
			throw new InvalidCredentials("Incorrect user key or secret!");
		}
		
		JwtDTO createdJWT = new JwtDTO(Jwt.generateToken(userExists.get().getUser_key().toString()),"Bearer ",7200);
		return createdJWT;
		
	}	
	
	public UserDTO findUserByEmail(String email) {
		
		Optional<User> userExists  = userRepository.findUserByEmail(email);
		
		if(userExists.isEmpty()) {
			throw new UserNotFound("User Not Found");
		}
		
		UserDTO user = new UserDTO(userExists.get());
		return user;
	}
}
