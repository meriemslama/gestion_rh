package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.contrat;
import com.example.demo.entities.typecontrat;



public interface typecontratservice  {
	ResponseEntity<Object> saveTypeContrat(typecontrat tc);
	
	  
	    void delettypeContratbyid(Long id);
	    public typecontrat gettypeContrat(Long id);
	    List<typecontrat>getalltypescontrat();
	    Page<typecontrat>getalltypecontrats(int page ,int size);
		  
	    Page<typecontrat>findByKeyword(String Keyword,int page ,int size);
		ResponseEntity<Object> updateTypeContrat(long id, typecontrat tc);

}
