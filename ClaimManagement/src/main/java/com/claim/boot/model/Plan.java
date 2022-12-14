package com.claim.boot.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.claim.boot.enums.PlanTypeEnum;

@Entity
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long planId;
	
	@Enumerated(EnumType.STRING)
	private PlanTypeEnum planType;
	
	private double insuredAmount;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@ManyToOne
	private Member member;

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public PlanTypeEnum getPlanType() {
		return planType;
	}

	public void setPlanType(PlanTypeEnum planType) {
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	

}
