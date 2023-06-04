package com.api.pay2you.utils;

import java.sql.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Jwt {

	private static String SECRET_KEY = "be979101cff8saasd8978aa5w5w5w54161bfa89fd86da46de1f0b9ba8efea7339b56a4333facc7b7f0d59394e62ef65c4a1d2fe";
	private static int horas = 2;

	public static String generateToken(String userKey) {
		Date now = new Date(0);
		Date expirationDate = new Date(now.getTime() + getExpirationTime(horas));

		return Jwts.builder().setSubject(userKey).setIssuedAt(now).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public static long getExpirationTime(int horas) {
		return horas * 60 * 60 * 1000L;
	}

	public static boolean isTokenExpired(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			return claims.getExpiration().before(new Date(0));
		} catch (ExpiredJwtException e) {
			return true; // O token já expirou
		} catch (Exception e) {
			return true; // Outro erro ao verificar o token (por exemplo, token inválido)
		}
	}
}
