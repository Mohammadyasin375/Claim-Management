package com.claim.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.claim.boot.enums.ClaimStatusEnum;
import com.claim.boot.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim,Long>{
	
	@Query("select c from Claim c where c.member.user.username=?1")
	List<Claim> getAllClaimsByUsername(String username);

	@Query("select c from Claim c where c.plan.planId=?1")
	Claim getClaimByPlanId(Long planId);

	@Query("select c from Claim c where c.status=?1")
	List<Claim> findAllPendingClaims(ClaimStatusEnum status);

	@Query("select c.member.memberId from Claim c where c.claimId=?1")
	Long findMemberId(Long claimId);
	
	
	
	

} 
