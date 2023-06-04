package com.api.pay2you.exceptions.user;

public class InvalidCredentials extends RuntimeException {
	private static final long serialVersionUID = 187545454512L;

	public InvalidCredentials(String message) {
		super(message);
	}
}
