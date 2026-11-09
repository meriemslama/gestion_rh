package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.congee;
import com.example.demo.entities.contrat;
import com.example.demo.entities.departement;




public interface contratservice  {
	ResponseEntity<Object> saveContrat(contrat c,long id,long idemp);
	
	  
	    void deletContratbyid(Long id);
	    public contrat getContrat(Long id);
	    List<contrat>getallContrats();
	    Page<contrat>getallcontrats(int page ,int size);
	    public List<Long> getAllEmployeeIdsFromCongee() ;
	    public List<Long> getAlltypesIdsFromCongee() ;
	    Page<contrat>findByKeyword(String Keyword,int page ,int size);
	    List<contrat>findByKeywordb(String Keyword);
		ResponseEntity<Object> updateContrat(long id, contrat c,long idtype,long idemp);
		
		List<contrat>getcontratbyemp(long id);
		 public List<Long> getAlltypesIdsFromContratByemp(long id) ;

}
