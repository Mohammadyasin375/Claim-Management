package com.claim.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.boot.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
