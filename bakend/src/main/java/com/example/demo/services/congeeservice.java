package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.congee;
import com.example.demo.entities.contrat;
import com.example.demo.entities.employee;




public interface congeeservice  {
	ResponseEntity<Object> savecongee(congee c,long id,long idemp);
	
	List<congee>findByKeywordb(String Keyword);
	    void deletCongeebyid(Long id);
	    public congee getCongee(Long id);
	    List<congee>getallCongees();
	    Page<congee>getallcongee(int page ,int size);
	    List<congee>dca();
	    List<congee>dcr();
	    Page<congee>findByKeyword(String Keyword,int page ,int size);
	    List<congee>npemployee();
	    List<congee>getconggeebyemp(long id);
	    public List<Long> getAllEmployeeIdsFromCongee() ;
	    public List<Long> getAllEmployeeIdsFromCongeeref() ;
	    public List<Long> getAllEmployeeIdsFromCongeeec() ;
	    public List<Long> getAllEmployeeIdsFromCongeeart() ;
	    public List<Long> getAlltypesIdsFromCongee() ;
	    public List<Long> getAlltypesIdsFromCongeeref() ;
	    public List<Long> getAlltypesIdsFromCongeeec() ;
	    public List<Long> getAlltypesIdsFromCongeeart() ;
	    public List<Long> getAlltypesIdsFromCongeeByemp(long id) ;
		ResponseEntity<Object> updateCongee(long id, congee c,long idtype,long idemp);
		ResponseEntity<Object> updateStatus(long id, String status);
		public List<congee> getcongeeencours() ;
		public List<congee> getcongeevalide() ;
		public List<congee> getcongeeinvalide() ;

}
