package com.api.pay2you.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.pay2you.dtos.JwtDTO;
import com.api.pay2you.dtos.LoginDTO;
import com.api.pay2you.exceptions.user.InvalidCredentials;
import com.api.pay2you.services.UserService;
import com.api.pay2you.utils.HttpResponse;

@RestController
@RequestMapping()
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO body) {
		try {
			JwtDTO loginIsValid = userService.login(body);
			return ResponseEntity.ok().body(loginIsValid);
		} catch (InvalidCredentials error) {

			HttpResponse errorResponse = new HttpResponse();
			errorResponse.setMessage(error.getMessage());
			errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
