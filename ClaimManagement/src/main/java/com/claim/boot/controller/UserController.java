package com.claim.boot.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.boot.model.User;
import com.claim.boot.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public User login(Principal principal) { 
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		user.setPassword("----------");
		return user;
	}
	
}
