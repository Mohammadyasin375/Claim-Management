package com.claim.boot.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.boot.enums.ClaimStatusEnum;
import com.claim.boot.model.Admin;
import com.claim.boot.model.Claim;
import com.claim.boot.model.User;
import com.claim.boot.repository.AdminRepository;
import com.claim.boot.repository.ClaimRepository;
import com.claim.boot.repository.UserRepository;
import com.claim.boot.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ClaimRepository claimRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// To Add Admin
	@PostMapping("/add")
	public void addAdmin(@RequestBody Admin admin) {

		User user = admin.getUser();
		user.setRole("ADMIN");

		admin.setJobTitle("ADMIN");

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);

		admin.setUser(user);
		admin.setCreatedAt(LocalDate.now());
		admin = adminRepository.save(admin);
		admin.getUser().setPassword("-------");
	}

	//To Approve claim
	@PutMapping("/approval/{status}/{id}")
	public ResponseEntity<String> approveClaim(@PathVariable("status") String status, @PathVariable("id") Long id) {
		ClaimStatusEnum approvalStatus = null;
		try {
			approvalStatus = ClaimStatusEnum.valueOf(status);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown Status");
		}
		
		/* Validate claim ID and fetch claim Details */
		Optional<Claim> optional = claimRepository.findById(id);

		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Claim ID is Invalid");

		Claim claim = optional.get();
		
		/* Update the status of this claim */
		claim.setStatus(approvalStatus);
		
		/*Save the claim */
		claimRepository.save(claim);
		return ResponseEntity.status(HttpStatus.OK).body("Claim status approved!");
	}
}
