package com.example.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import com.example.employee.enteties.employee;
import com.example.demo.entities.typecongee;

@Repository
public interface typecongeerepository extends JpaRepository<typecongee, Long>{
	 @Query(value = "select * from typecongee  where typecongee.type like %:keyword% ", nativeQuery = true)
	 Page<typecongee> findByKeyword(@Param("keyword") String keyword,Pageable page);
	 
	 

}
