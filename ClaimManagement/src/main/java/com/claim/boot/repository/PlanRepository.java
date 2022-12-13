package com.claim.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.claim.boot.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{
    
	@Query("select p from Plan p where p.member.user.username=?1")
	List<Plan> getAllPlansByUsername(String username);

}
