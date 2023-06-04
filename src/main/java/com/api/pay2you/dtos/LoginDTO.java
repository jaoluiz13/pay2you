package com.api.pay2you.dtos;

import java.util.UUID;

public class LoginDTO {

	private UUID user_key;
	private String user_secret;
	
	public LoginDTO() {
		
	}
	
	public LoginDTO(UUID user_key, String user_secret) {
		this.user_key = user_key;
		this.user_secret = user_secret;
	}



	public UUID getUser_key() {
		return user_key;
	}

	public void setUser_key(UUID user_key) {
		this.user_key = user_key;
	}

	public String getUser_secret() {
		return user_secret;
	}

	public void setUser_secret(String user_secret) {
		this.user_secret = user_secret;
	}
	
	
}
