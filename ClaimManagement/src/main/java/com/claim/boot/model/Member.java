package com.claim.boot.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long memberId;
	
	private String memberName;
	private LocalDate dob;
	
	@Column(name = "mobileno",nullable = false,unique = true)
	private String mobileNo;
	
	private String city;
	private String state;
	private String hNo;
	
	private double balanceInsuredAmount = 0.0;
	
	@OneToOne()
	private User user;
	
	@OneToOne()
	private Plan plan;
	
	@OneToOne()
	private Document document;

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String gethNo() {
		return hNo;
	}

	public void sethNo(String hNo) {
		this.hNo = hNo;
	}

	public double getBalanceInsuredAmount() {
		return balanceInsuredAmount;
	}

	public void setBalanceInsuredAmount(double balanceInsuredAmount) {
		this.balanceInsuredAmount = balanceInsuredAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(long memberId, String memberName, LocalDate dob, String mobileNo, String city, String state,
			String hNo, double balanceInsuredAmount, User user, Plan plan, Document document) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.dob = dob;
		this.mobileNo = mobileNo;
		this.city = city;
		this.state = state;
		this.hNo = hNo;
		this.balanceInsuredAmount = balanceInsuredAmount;
		this.user = user;
		this.plan = plan;
		this.document = document;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", dob=" + dob + ", mobileNo=" + mobileNo
				+ ", city=" + city + ", state=" + state + ", hNo=" + hNo + ", balanceInsuredAmount="
				+ balanceInsuredAmount + ", user=" + user + ", plan=" + plan + ", document=" + document + "]";
	}
}
