package com.claim.boot.dto;

import java.time.LocalDate;

import com.claim.boot.enums.PlanTypeEnum;

public class PlanResponseDto {
	Long planId;
    String planType;
    double insuredAmount;
    LocalDate startDate;
    LocalDate endDate;
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public double getInsuredAmount() {
		return insuredAmount;
	}
	public void setInsuredAmount(double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
}
