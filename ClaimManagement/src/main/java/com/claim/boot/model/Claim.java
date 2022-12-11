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
import javax.persistence.OneToOne;

import com.claim.boot.enums.ClaimStatusEnum;

@Entity
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long claimId;

	@ManyToOne()
	private Member member;

	@Column(length = 10, columnDefinition = "varchar(32)")
	@Enumerated(EnumType.STRING)
	private ClaimStatusEnum status;

	private double claimAmount;
	private String hospitalName;
	private LocalDate admitDate;
	private LocalDate dischargeDate;
	private String claimType;
	private String remarks = "";
	private LocalDate claimDate;
	
	@OneToOne()
	private Document document;

	public long getClaimId() {
		return claimId;
	}

	public void setClaimId(long claimId) {
		this.claimId = claimId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public ClaimStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ClaimStatusEnum status) {
		this.status = status;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public LocalDate getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(LocalDate admitDate) {
		this.admitDate = admitDate;
	}

	public LocalDate getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Claim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Claim(long claimId, Member member, ClaimStatusEnum status, double claimAmount, String hospitalName,
			LocalDate admitDate, LocalDate dischargeDate, String claimType, String remarks, LocalDate claimDate,
			Document document) {
		super();
		this.claimId = claimId;
		this.member = member;
		this.status = status;
		this.claimAmount = claimAmount;
		this.hospitalName = hospitalName;
		this.admitDate = admitDate;
		this.dischargeDate = dischargeDate;
		this.claimType = claimType;
		this.remarks = remarks;
		this.claimDate = claimDate;
		this.document = document;
	}

	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", member=" + member + ", status=" + status + ", claimAmount="
				+ claimAmount + ", hospitalName=" + hospitalName + ", admitDate=" + admitDate + ", dischargeDate="
				+ dischargeDate + ", claimType=" + claimType + ", remarks=" + remarks + ", claimDate=" + claimDate
				+ ", document=" + document + "]";
	}
}
