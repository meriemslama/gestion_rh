package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


import com.example.demo.entities.typecongee;



public interface typecongeeservice  {
	ResponseEntity<Object> saveTypeCongee(typecongee tc);
	
	  
	    void delettypeCongeebyid(Long id);
	    public typecongee gettypeCongee(Long id);
	    List<typecongee>getalltypescongees();
	    Page<typecongee>getalltypecongee(int page ,int size);
		  
	    Page<typecongee>findByKeyword(String Keyword,int page ,int size);
		ResponseEntity<Object> updateTypeCongee(long id, typecongee tc);

}
