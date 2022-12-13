package com.claim.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.claim.boot.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
	
	@Query("select a from Admin a where a.user.username=?1")
	Admin getAdminByUsername(String username);

}
