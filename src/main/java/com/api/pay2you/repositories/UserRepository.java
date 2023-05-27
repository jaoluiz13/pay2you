package com.api.pay2you.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.pay2you.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.document = :document")
    User findUserByDocument(String document);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(String email);
}
