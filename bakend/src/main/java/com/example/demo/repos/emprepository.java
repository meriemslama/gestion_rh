package com.example.demo.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entities.employee;

@Repository

public interface emprepository extends JpaRepository<employee, Long> {
 @Query(value = "select * from employee  where employee.nom like %:keyword% or employee.prenom like %:keyword% or employee.poste like %:keyword% or employee.gender like %:keyword%", nativeQuery = true)
 Page<employee> findByKeyword(@Param("keyword") String keyword,Pageable page);
 @Query(value = "select count(*) from employee  where employee.gender = 'femme' ", nativeQuery = true)
 int employeesfemme();
 @Query(value = "select count(*) from employee  where employee.gender = 'homme' ", nativeQuery = true)
 int employeeshomme();
 public employee findByEmail(String email);


}
