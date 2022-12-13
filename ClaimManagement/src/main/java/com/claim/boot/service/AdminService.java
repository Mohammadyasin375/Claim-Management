package com.claim.boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.claim.boot.dto.AdminResponseDto;
import com.claim.boot.model.Admin;
import com.claim.boot.model.User;
import com.claim.boot.repository.AdminRepository;
import com.claim.boot.repository.ClaimRepository;
import com.claim.boot.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClaimRepository claimRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Admin addAdmin(Admin admin) {

		User user = admin.getUser();
		user.setRole("ADMIN");

		admin.setJobTitle("ADMIN");

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);

		admin.setUser(user);
		admin.setCreatedAt(LocalDate.now());
		admin = adminRepository.save(admin);
		admin.getUser().setPassword("-------");
		return admin;
	}

	public List<AdminResponseDto> getAllAdmins() {
		List<Admin> list = adminRepository.findAll();
		List<AdminResponseDto> listDto = new ArrayList<>();
		for (Admin a : list) {
			AdminResponseDto dto = new AdminResponseDto(); // id,name -- //200X
			dto.setId(a.getAdminId()); // 200X (id)
			dto.setName(a.getName());// 200X (id,name)
			listDto.add(dto); // [100X,200X]
		}
		return listDto;
	}
	
	public AdminResponseDto getAdminDetails(String username) {
		Admin admin = adminRepository.getAdminByUsername(username);
		
		AdminResponseDto dto = new AdminResponseDto();
		
		dto.setId(admin.getAdminId());
		dto.setName(admin.getName());
		
		return dto;
		
	}

}
