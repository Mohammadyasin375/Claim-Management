package com.claim.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.claim.boot.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim,Long>{
	
	@Query("select c from Claim c where c.member.user.username=?1")
	List<Claim> getAllClaimsByUsername(String username);

} 
