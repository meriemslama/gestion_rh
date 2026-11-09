package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.congee;
import com.example.demo.entities.typecongee;
import com.example.demo.repos.typecongeerepository;

import jakarta.transaction.Transactional;


@Service
public class typecongeeserviceimp implements typecongeeservice{

	@Autowired
   typecongeerepository typecongeerepository;
	@Override
	@CrossOrigin
	@Transactional
	public ResponseEntity<Object> saveTypeCongee(typecongee tc) {
		// TODO Auto-generated method stub
		typecongeerepository
        .findById(tc.getId())
        .ifPresentOrElse(
                tcong -> {
                    throw new ResponseStatusException(HttpStatus.FOUND, "type de congee ID alredy exist");
                }, () -> {
                	typecongeerepository.save(tc);
                }
        );
		  return ResponseEntity.ok(tc);
	}
	@Override
	public typecongee gettypeCongee(Long id) {
		// TODO Auto-generated method stub
		return  typecongeerepository.findById(id).get();
	}

	@Override
	@Transactional
	public ResponseEntity<Object> updateTypeCongee(long id, typecongee tc) {
		// TODO Auto-generated method stub
		 typecongeerepository
         .findById(id)
         .ifPresentOrElse(tcong -> {
        	 tcong.setType(tc.getType());
        	 tcong.setCongee(tc.getCongee());
     
        	
        	 typecongeerepository.save(tcong);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type congee to update not found");
         });
		 // return ResponseEntity.accepted().body("Successfully updated acte");
		 return ResponseEntity.ok(tc);	}

	@Override
	@Transactional
	


	public void delettypeCongeebyid(Long id) {
		// TODO Auto-generated method stub
		 typecongeerepository
         .findById(id)
         .ifPresentOrElse(tcong -> {
        	 typecongeerepository.delete(tcong);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type of congee to delete not found");
         });	

		
	}

	

	@Override
	public List<typecongee> getalltypescongees() {
		//List<departement>departements=new ArrayList<>();
		return typecongeerepository.findAll();
		// return departements;
	}
	public Page<typecongee> getalltypecongee(int page ,int size) {
		//List<departement>departements=new ArrayList<>();
		Pageable pages=PageRequest.of(page, size);
		return typecongeerepository.findAll(pages);
		// return departements;
	}
	
	public Page<typecongee> findByKeyword( String Keyword, int page, int size) {
		Pageable pages=PageRequest.of(page, size);
		return typecongeerepository.findByKeyword(Keyword,pages);
	}
	
	

}
