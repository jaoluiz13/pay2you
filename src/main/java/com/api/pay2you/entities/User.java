package com.api.pay2you.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String document;
	private UUID user_client;
	private String user_secret;
	private String email;
	private String name;
	private String recovery_pass_token;
	private Instant created_at;
	
	public User() {
		
	}

	public User(String id, String document, UUID user_client, String user_secret, String email, String name,
			String recovery_pass_token, Instant created_at) {
		super();
		this.id = id;
		this.document = document;
		this.user_client = user_client;
		this.user_secret = user_secret;
		this.email = email;
		this.name = name;
		this.recovery_pass_token = recovery_pass_token;
		this.created_at = created_at;
	}

	public String getId() {
		return id;
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

	public UUID getUser_client() {
		return user_client;
	}

	public void setUser_client(UUID user_client) {
		this.user_client = user_client;
	}

	public String getUser_secret() {
		return user_secret;
	}

	public void setUser_secret(String user_secret) {
		this.user_secret = user_secret;
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
		return Objects.hash(created_at, document, email, id, name, recovery_pass_token, user_client, user_secret);
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
		return Objects.equals(created_at, other.created_at) && Objects.equals(document, other.document)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(recovery_pass_token, other.recovery_pass_token)
				&& Objects.equals(user_client, other.user_client) && Objects.equals(user_secret, other.user_secret);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", document=" + document + ", user_client=" + user_client + ", user_secret="
				+ user_secret + ", email=" + email + ", name=" + name + ", recovery_pass_token=" + recovery_pass_token
				+ ", created_at=" + created_at + "]";
	}
	
}
