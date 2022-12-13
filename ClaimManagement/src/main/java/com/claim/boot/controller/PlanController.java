package com.claim.boot.controller;

import java.security.Principal;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.boot.model.Plan;

import com.claim.boot.service.PlanService;

@RestController
@RequestMapping("/api/plan")
@CrossOrigin("http://localhost:8903/")
public class PlanController {

	@Autowired
	private PlanService planService;

	@PostMapping("/add")
	public ResponseEntity<String> postPlan(Principal principal, @RequestBody Plan plan) {
		String username = principal.getName();

		return planService.postPlan(username, plan);

	}
	
	@GetMapping("/all")
	public List<Plan>getAllPlansByUsername(Principal principal) {
		String username=principal.getName();
		
		return planService.getAllPlansByUsername(username);
		
	}
	
	
}
