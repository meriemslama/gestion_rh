package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;
import com.example.demo.entities.role;
import com.example.demo.entities.typecontrat;


@Repository
public interface userrepository extends JpaRepository<User, Long>{
	 @Query(value = "select * from user  where user.email like %:keyword% OR user.etat like %:keyword% or user.role like %:keyword%", nativeQuery = true)
	 Page<User> findByKeyword(@Param("keyword") String keyword,Pageable page);
	 Optional<User>findByEmail(String email);
}