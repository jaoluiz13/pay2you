package com.api.pay2you.config;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.pay2you.entities.User;
import com.api.pay2you.repositories.UserRepository;
import com.api.pay2you.utils.ApiUtils;

@Configuration
@Profile("local")
public class Seeder implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Value("${app.seed.enabled}")
	private boolean seedEnabled;

	private void seedUsersTable() {

		User user = new User(null, "57971474083", UUID.randomUUID(), ApiUtils.generatePassword(), "jxvxluiz@gmail.com",
				"João Luiz", "", Instant.now());

		userRepository.save(user);
		System.out.println(user);

	}

	@Override
	public void run(String... args) throws Exception {
		if (seedEnabled) {
			seedUsersTable();
		}

	}
}
