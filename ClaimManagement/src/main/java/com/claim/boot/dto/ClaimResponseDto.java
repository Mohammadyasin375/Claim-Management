package com.claim.boot.dto;

import java.time.LocalDate;

import com.claim.boot.enums.ClaimStatusEnum;

public class ClaimResponseDto {
	private Long claimId;
	private Long memberId;
	private String claimType;
	private double claimAmount;
	private ClaimStatusEnum status; 
	private LocalDate claimDate;
	public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public ClaimStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ClaimStatusEnum status) {
		this.status = status;
	}
	public LocalDate getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}
	
		
}
