package com.claim.boot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.boot.dto.AdminResponseDto;

import com.claim.boot.model.Admin;

import com.claim.boot.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("http://localhost:8903/")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add")
	public Admin addAdmin(@RequestBody Admin admin) {

		return adminService.addAdmin(admin);
	}

	@GetMapping("/all")
	public List<AdminResponseDto> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@PostMapping("/approval/{status}/{cId}")
	public ResponseEntity<String> approveClaim(@PathVariable("status") String status, @PathVariable("cId") Long cId) {
		return adminService.approveClaim(status, cId);
	}

	@GetMapping("/details")
	public AdminResponseDto getAdminDetails(Principal principal) {
		String username = principal.getName();
		return adminService.getAdminDetails(username);
	}
}
