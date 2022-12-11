package com.claim.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.boot.model.Claim;
import com.claim.boot.repository.ClaimRepository;

@Service
public class ClaimService {
	@Autowired
	private ClaimRepository claimRepository;
	
	public void insertClaim(Claim claim) {
		claimRepository.save(claim);
	}
	
	public List<Claim> getAllClaims(){
		List<Claim> claims = claimRepository.findAll();
		return claims;
	}
	
	public Claim getClaimById(long id) {
		return claimRepository.findById(id).get();
	}
	
	public void deleteClaimById(long id) {
		claimRepository.deleteById(id);
	}
}
