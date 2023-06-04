package com.api.pay2you.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.pay2you.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.document = :document")
    Optional<User> findUserByDocument(String document);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.user_key = :key")
    Optional<User> findUserByKey(UUID key);
}
