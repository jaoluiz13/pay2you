package com.api.pay2you.dtos;

public class JwtDTO {
	private String token;
	private String tokenType;
	private Integer expiresIn;
	
	public JwtDTO() {
		
	}

	public JwtDTO(String token, String tokenType, Integer expiresIn) {
		this.token = token;
		this.tokenType = tokenType;
		this.expiresIn = expiresIn;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}	
}
