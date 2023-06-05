package com.api.pay2you.filters;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.pay2you.exceptions.jwt.InvalidJwt;
import com.api.pay2you.repositories.UserRepository;
import com.api.pay2you.utils.Jwt;

@Component
public class FilterToken {

	@Autowired
	private UserRepository userRepository;

	public Boolean authFilter(String header) {
		String token;

		if (header != null) {
			token = header.replace("Bearer ", "");

			try {
				var subject = Jwt.getSubject(token);
				var usuario = userRepository.findUserByKey(UUID.fromString(subject));
				if (usuario.isEmpty()) {
					throw new InvalidJwt("Invalid Token");
				}
			} catch (InvalidJwt error) {
				throw new InvalidJwt(error.getMessage());
			}

			return true;

		} else {
			throw new InvalidJwt("Invalid Token");
		}
	}

}
