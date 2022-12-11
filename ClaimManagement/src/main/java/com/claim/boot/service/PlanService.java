package com.claim.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.claim.boot.model.Plan;
import com.claim.boot.repository.PlanRepository;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;
	
	public void insertNewPlan(Plan plan) {
		planRepository.save(plan);
		System.out.println("Inserted plan details!");
	}
	
	public List<Plan> getAllPlans(){
		List<Plan> plans = planRepository.findAll();
		return plans;
	}
	
	public Plan getPlanById(long id) {
		Plan plan = planRepository.findById(id).get();
		return plan;
	}
	
	public void deletePlanById(long id) {
		planRepository.deleteById(id);
	}
}
