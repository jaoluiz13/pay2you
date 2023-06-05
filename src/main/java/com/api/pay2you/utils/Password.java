package com.api.pay2you.utils;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Password {

	public static String generatePassword() {

		final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
		final String SPECIAL_CHARS = "!@#$%^&*()-_=+[{]};:'\"\\|<,>.?/";
		final String ALL_CHARS = UPPERCASE_CHARS + LOWERCASE_CHARS + SPECIAL_CHARS;
		final int PASSWORD_LENGTH = 25;

		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder();

		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int randomIndex = random.nextInt(ALL_CHARS.length());
			password.append(ALL_CHARS.charAt(randomIndex));
		}

		return password.toString();
	}

	public static String PasswordEncode(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	public static boolean PasswordMatcher(String rawPassword, String hashedPassword) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, hashedPassword);

	}
}
