package com.api.pay2you.exceptions.jwt;

public class JwtNotFound extends RuntimeException {
private static final long serialVersionUID = 1235L;
	
	public JwtNotFound(String message) {
		super(message);
	}
}
