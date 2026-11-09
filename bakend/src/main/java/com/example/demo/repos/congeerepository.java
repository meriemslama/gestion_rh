package com.example.demo.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.congee;


import jakarta.transaction.Transactional;

//import com.example.employee.enteties.employee;
@Transactional
@Repository
public interface congeerepository extends JpaRepository<congee, Long> {
	 @Query(value = "select * from congee  where congee.date_debut like %:keyword% ", nativeQuery = true)
	 Page<congee> findByKeyword(@Param("keyword") String keyword,Pageable page);
	 
	 /*@Query(value = "select nom ,prenom from employee where employee.id IN (select employee_id from congee)", nativeQuery = true)
	 List<employee> npemployee();*/
	/* @Query(value = "select employee_id from congee", nativeQuery = true)
	 List<congee> npemployee();*/
	 @Query(value ="SELECT c.employee_id FROM Congee c", nativeQuery = true)
	    List<Long> findAllEmployeeIds();
	 
	 @Query(value ="SELECT c.employee_id FROM Congee c where c.status='accepte par admin'", nativeQuery = true)
	    List<Long> findAllEmployeeIdsac();
	 
	 @Query(value ="SELECT c.employee_id FROM Congee c where c.status='refuse par admin'", nativeQuery = true)
	    List<Long> findAllEmployeeIdsref();
	 @Query(value ="SELECT c.employee_id FROM Congee c where c.status='en cours'", nativeQuery = true)
	    List<Long> findAllEmployeeIdsec();
	 @Query(value ="SELECT c.employee_id FROM Congee c where c.status='accepte par responsable temporairement'", nativeQuery = true)
	    List<Long> findAllEmployeeIdsart();
	 
	 @Query(value ="SELECT c.typecongee_id FROM Congee c", nativeQuery = true)
		   List<Long> findAllTypesIdsc();
		 
	@Query(value ="SELECT c.typecongee_id FROM Congee c where c.status='accepte par admin'", nativeQuery = true)
		    List<Long> findAllTypesIdscac();
	
	@Query(value ="SELECT c.typecongee_id FROM Congee c where c.status='refuse par admin'", nativeQuery = true)
    List<Long> findAllTypesIdscref();
	
	@Query(value ="SELECT c.typecongee_id FROM Congee c where c.status='en cours'", nativeQuery = true)
    List<Long> findAllTypesIdscec();
	@Query(value ="SELECT c.typecongee_id FROM Congee c where c.status='accepte par responsable temporairement'", nativeQuery = true)
    List<Long> findAllTypesIdscart();
	
		 @Query(value ="SELECT * FROM Congee c where c.employee_id=:id", nativeQuery = true)
		 List<congee> getcongeesbyemp(@Param("id") Long id);
		 @Query(value ="SELECT c.typecongee_id FROM Congee c where c.employee_id=:id", nativeQuery = true)
		    List<Long> findAllTypesIdscbyemp(@Param("id") Long id);
		 @Query(value ="SELECT * FROM Congee c where c.status='en cours'", nativeQuery = true)
		    List<congee> congeeencours();
		    @Query(value ="SELECT * FROM Congee c where c.status='accepte par admin'", nativeQuery = true)
		    List<congee> congeevalide();
		    @Query(value ="SELECT * FROM Congee c where c.status='refuse par admin'", nativeQuery = true)
		    List<congee> congeeinvalide();
		    @Query(value = "select * from congee  where congee.status='accepte par responsable temporairement' ", nativeQuery = true)
			 List<congee> dca();
			 @Query(value = "select * from congee  where congee.status='en cours' ", nativeQuery = true)
			 List<congee> dcr();
			 
			 
			 @Query(value = "select * from congee  where congee.date_debut like %:keyword% ", nativeQuery = true)
			 List<congee> findByKeywordb(@Param("keyword") String keyword);
		 

}
