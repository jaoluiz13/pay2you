package com.api.pay2you.exceptions.user;

public class UserAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExists(String message) {
		super(message);
	}
}
