package com.claim.boot.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long adminId;
	
	private String name;

	private String jobTitle;
	
	private LocalDate createdAt;
	
	@OneToOne
	private User user;

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(long adminId, String name, String jobTitle, LocalDate createdAt, User user) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.jobTitle = jobTitle;
		this.createdAt = createdAt;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", name=" + name + ", jobTitle=" + jobTitle + ", createdAt=" + createdAt
				+ ", user=" + user + "]";
	}
	
}
