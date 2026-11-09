package com.example.demo.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.congee;
import com.example.demo.entities.role;

public interface rolerepos extends JpaRepository<role, Long>{
	 @Query(value = "select * from role  where role.role like %:keyword% ", nativeQuery = true)
	 Page<role> findByKeyword(@Param("keyword") String keyword,Pageable page);
	 @Query(value = "select * from role where idt Not IN (select role_id from user_role where user_id=:userid) ", nativeQuery = true)
	 List<role> getNotRoles(@Param("userid") Long userid);
}
