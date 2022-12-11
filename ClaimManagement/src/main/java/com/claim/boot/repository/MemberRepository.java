package com.claim.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.claim.boot.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	@Query("select m from Member m where m.user.username=?1")
	Member getMemberByUsername(String username);
}
