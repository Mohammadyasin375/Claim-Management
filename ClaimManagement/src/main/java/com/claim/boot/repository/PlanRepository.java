package com.claim.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.boot.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

}
