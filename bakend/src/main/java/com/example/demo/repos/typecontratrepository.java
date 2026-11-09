package com.example.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.contrat;
//import com.example.employee.enteties.employee;
import com.example.demo.entities.typecontrat;

@Repository
public interface typecontratrepository extends JpaRepository<typecontrat, Long>{
	 @Query(value = "select * from typecontrat  where typecontrat.type like %:keyword% ", nativeQuery = true)
	 Page<typecontrat> findByKeyword(@Param("keyword") String keyword,Pageable page);

}
