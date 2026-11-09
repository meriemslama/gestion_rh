package com.example.demo.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.congee;
import com.example.demo.entities.contrat;


@Repository
public interface contratrepository extends JpaRepository<contrat, Long>{
	 @Query(value = "select * from contrat  where contrat.date_debut like %:keyword% ", nativeQuery = true)
	 Page<contrat> findByKeyword(@Param("keyword") String keyword,Pageable page);
	 @Query(value ="SELECT c.employee_id FROM Contrat c ORDER BY c.id", nativeQuery = true)
	    List<Long> findAllEmployeeIdsc();
	 @Query(value ="SELECT c.typecontrat_id FROM Contrat c ORDER BY c.id", nativeQuery = true)
	    List<Long> findAllTypesIdsc();
	 @Query(value ="SELECT * FROM Contrat c where c.employee_id=:id", nativeQuery = true)
	 List<contrat> getcontratssbyemp(@Param("id") Long id);
	 @Query(value ="SELECT c.typecontrat_id FROM Contrat c where c.employee_id=:id ", nativeQuery = true)
	    List<Long> findAllTypesIdscbyemp(@Param("id") Long id);
	 @Query(value = "select * from contrat  where contrat.date_debut like %:keyword% ", nativeQuery = true)
	 List<contrat> findByKeywordb(@Param("keyword") String keyword);
}
