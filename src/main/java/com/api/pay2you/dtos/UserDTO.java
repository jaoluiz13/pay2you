package com.api.pay2you.dtos;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.api.pay2you.entities.User;
import com.api.pay2you.utils.ApiUtils;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class UserDTO {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String document;
	private UUID user_key;
	private String user_secret;
	private String email;
	private String name;
	private String recovery_pass_token;
	private Instant created_at;

	public UserDTO() {

	}

	public UserDTO(String id, String document, String email, String name, String recovery_pass_token) {
		
		this.id = id;
		this.document = document;
		this.user_key = generateUserKey();
		this.user_secret = generateUserSecret();
		this.email = email;
		this.name = name;
		this.recovery_pass_token = recovery_pass_token;
		this.created_at = Instant.now();
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.document = user.getDocument();
		this.user_key = user.getUser_key();
		this.user_secret = user.getUser_secret();
		this.email = user.getEmail();
		this.name = user.getName();
		this.recovery_pass_token = user.getRecovery_pass_token();
		this.created_at = user.getCreated_at();
	}

	public String getId() {
		return id;
	}

	public UUID generateUserKey() {
		UUID userKey = UUID.randomUUID();
		setUser_key(userKey);
		return userKey;
	}

	public String generateUserSecret() {
		String generatedPassword = ApiUtils.generatePassword();
		setUser_secret(generatedPassword);
		return generatedPassword;
	}
	
	public void setUser_key(UUID user_client) {
		this.user_key = user_client;
	}

	public void setUser_secret(String user_secret) {
		this.user_secret = user_secret;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public UUID getUser_key() {
		return user_key;
	}

	public String getUser_secret() {
		return user_secret;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecovery_pass_token() {
		return recovery_pass_token;
	}

	public void setRecovery_pass_token(String recovery_pass_token) {
		this.recovery_pass_token = recovery_pass_token;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created_at, document, email, id, name, recovery_pass_token, user_key, user_secret);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(created_at, other.getCreated_at()) && Objects.equals(document, other.getDocument())
				&& Objects.equals(email, other.getEmail()) && Objects.equals(id, other.getId())
				&& Objects.equals(name, other.getName())
				&& Objects.equals(recovery_pass_token, other.getRecovery_pass_token())
				&& Objects.equals(user_key, other.getUser_key())
				&& Objects.equals(user_secret, other.getUser_secret());
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", document=" + document + ", user_key=" + user_key + ", user_secret="
				+ user_secret + ", email=" + email + ", name=" + name + ", recovery_pass_token=" + recovery_pass_token
				+ ", created_at=" + created_at + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
