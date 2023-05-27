package com.api.pay2you.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.pay2you.dtos.UserDTO;
import com.api.pay2you.exceptions.user.UserAlreadyExists;
import com.api.pay2you.services.UserService;
import com.api.pay2you.utils.HttpResponse;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<?> createUser(@RequestBody UserDTO body){
		
		try {
			UserDTO user = userService.createUser(body);
			return ResponseEntity.ok().body(user);
		}catch(UserAlreadyExists error) {
			
			HttpResponse errorResponse = new HttpResponse();
			errorResponse.setMessage(error.getMessage());
			errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
}
