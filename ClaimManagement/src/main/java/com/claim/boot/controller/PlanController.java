package com.claim.boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.boot.model.Plan;
import com.claim.boot.repository.PlanRepository;
import com.claim.boot.service.PlanService;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
	
	@Autowired
	private PlanService planService; 
	
	@Autowired
	private PlanRepository planRepository;
	
	//To add plan
	@PostMapping("/add")
	public ResponseEntity<String> addPlan(@RequestBody Plan plan) {
		planRepository.save(plan);
		return ResponseEntity.status(HttpStatus.OK).body("Plan added succesfully!");
	}

	//To get All Plans
	@GetMapping("/getAll")
	public List<Plan> getAllPlans(){
		List<Plan> plans = planService.getAllPlans();
		return plans;
	}
	
	//To get plan By ID
	@GetMapping("/{id}")
	public Plan getPlanById(@PathVariable("id") long id) {
		
		Optional<Plan> optional = planRepository.findById(id);
		if(!optional.isPresent())
			System.out.println(ResponseEntity.status(HttpStatus.OK).body("Invalid Plan ID!"));
		
		Plan plan = optional.get();
		
		return plan;
	}
	
	//To delete plan
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<String> deletePlanById(@PathVariable("id") long id) {
//		planService.deletePlanById(id);
//		return ResponseEntity.status(HttpStatus.OK).body("Deleted plan succesully!");
//	}
	
}
