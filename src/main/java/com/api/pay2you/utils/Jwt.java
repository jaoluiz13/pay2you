package com.api.pay2you.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Component;

import com.api.pay2you.exceptions.jwt.InvalidJwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

@Component
public class Jwt {

	private static String SECRET_KEY = "be979101cff8saasd8978aa5w5w5w54161bfa89fd86da46de1f0b9ba8efea7339b56a4333facc7b7f0d59394e62ef65c4a1d2fe";
	private static int hours = 2;

	public static String generateToken(String userKey) {
		/*
		 * Date now = new Date(0); Date expirationDate = new Date(now.getTime() +
		 * getExpirationTime(horas));
		 * 
		 * return Jwts.builder().setSubject(userKey).setIssuedAt(now).setExpiration(
		 * expirationDate) .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
		 */
		
		return JWT.create().withIssuer("User").withSubject(userKey).withExpiresAt(
				LocalDateTime.now()
					.plusMinutes(getExpiration(hours)).toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC512(SECRET_KEY));

	}

	public static int getExpiration(int hours) {
		return hours * 60;
	}
	
	public static String getSubject(String token) {
		try {			
			return JWT.require(Algorithm.HMAC512(SECRET_KEY)).withIssuer("User").build().verify(token).getSubject();
		}catch(TokenExpiredException error) {
			throw new InvalidJwt(error.getMessage());
		}
	}

}
