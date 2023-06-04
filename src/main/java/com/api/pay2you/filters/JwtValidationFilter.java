package com.api.pay2you.filters;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.api.pay2you.exceptions.jwt.InvalidJwt;
import com.api.pay2you.utils.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Obtenha o token JWT do cabeçalho da solicitação
		String token = extractTokenFromRequest(request);

		// Valide o token
		if (token == null && Jwt.isTokenExpired(token)) {
			throw new InvalidJwt("Invalid Token");
		}

		filterChain.doFilter(request, response);
	}

	private String extractTokenFromRequest(HttpServletRequest request) {
		// Extrair o token do cabeçalho Authorization
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}