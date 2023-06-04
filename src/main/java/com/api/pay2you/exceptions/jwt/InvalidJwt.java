package com.api.pay2you.exceptions.jwt;

public class InvalidJwt extends RuntimeException  {

	private static final long serialVersionUID = 1235L;
	
	public InvalidJwt(String message) {
		super(message);
	}

}
