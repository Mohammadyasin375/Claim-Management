package com.claim.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.boot.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{

}
