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
import com.example.demo.entities.contrat;
import com.example.demo.entities.departement;
import com.example.demo.entities.typecongee;
import com.example.demo.repos.congeerepository;
import com.example.demo.entities.employee;


import jakarta.transaction.Transactional;


@Service
public class congeeserviceimp implements congeeservice{

	@Autowired
   congeerepository congeerepository;
	typecongee t;
	typecongee tu;
	com.example.demo.entities.employee employee;
	com.example.demo.entities.employee employeeu;
	@Autowired
	typecongeeservice type;
	@Autowired
	empservice emp;
	@Override
	@CrossOrigin
	@Transactional
	public ResponseEntity<Object> savecongee(congee c,long id,long idemp) {
		// TODO Auto-generated method stub
		t=type.gettypeCongee( id);
		employee=emp.getEmployee(idemp);
		congeerepository
        .findById(c.getId())
        .ifPresentOrElse(
                dept -> {
                    throw new ResponseStatusException(HttpStatus.FOUND, "congee ID alredy exist");
                }, () -> {
                	c.setEmployee(employee);
                	c.setTypecongee(t);
                	congeerepository.save(c);
                }
        );
		  return ResponseEntity.ok(c);
	}
	@Override
	public congee getCongee(Long id) {
		// TODO Auto-generated method stub
		return  congeerepository.findById(id).get();
	}

	@Override
	@Transactional
	public ResponseEntity<Object> updateCongee(long id, congee c,long idtype,long idemp) {
		// TODO Auto-generated method stub
		tu=type.gettypeCongee( idtype);
		employeeu=emp.getEmployee( idemp);
		c.setEmployee(employeeu);
		c.setTypecongee(tu);
		 congeerepository
         .findById(id)
         .ifPresentOrElse(cong -> {
        	 cong.setDateDebut(c.getDateDebut());
        	 cong.setDateFin(c.getDateFin());
        	// cong.setEmployees(c.getEmployees());
        	 cong.setEmployee(c.getEmployee());
        	 cong.setTypecongee(c.getTypecongee());
        	 cong.setStatus(c.getStatus());
        	 congeerepository.save(cong);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "congee to update not found");
         });
		 // return ResponseEntity.accepted().body("Successfully updated acte");
		 return ResponseEntity.ok(c);	}
	@Override
	@Transactional
	public ResponseEntity<Object> updateStatus(long id, String status) {
		// TODO Auto-generated method stub
		congee congee=this.getCongee(id);
		 congeerepository
         .findById(id)
         .ifPresentOrElse(cong -> {
        	 cong.setStatus(status);
        	
        	
        	 congeerepository.save(cong);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "congee to update not found");
         });
		 // return ResponseEntity.accepted().body("Successfully updated acte");
		 return ResponseEntity.ok(congee);	}

	@Override
	@Transactional
	


	public void deletCongeebyid(Long id) {
		// TODO Auto-generated method stub
		 congeerepository
         .findById(id)
         .ifPresentOrElse(cong -> {
        	 congeerepository.delete(cong);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "congee to delete not found");
         });	

		
	}

	

	@Override
	public List<congee> getallCongees() {
		//List<departement>departements=new ArrayList<>();
		return congeerepository.congeevalide();
		// return departements;
	}
	@Override
	public Page<congee> getallcongee(int page ,int size) {
		//List<departement>departements=new ArrayList<>();
		Pageable pages=PageRequest.of(page, size);
		return congeerepository.findAll(pages);
		// return departements;
	}
	
	public Page<congee> findByKeyword( String Keyword, int page, int size) {
		Pageable pages=PageRequest.of(page, size);
		return congeerepository.findByKeyword(Keyword,pages);
	}
	@Override
	/*public List<congee> npemployee() {
		// TODO Auto-generated method stub

		return congeerepository.npemployee();
	}*/
	public List<Long> getAllEmployeeIdsFromCongee() {
	    return congeerepository.findAllEmployeeIdsac();
	}
	@Override
	public List<congee> npemployee() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Long> getAlltypesIdsFromCongee() {
		// TODO Auto-generated method stub
		 return congeerepository.findAllTypesIdscac();
	}
	@Override
	public List<congee> getconggeebyemp(long id) {
		// TODO Auto-generated method stub
		return congeerepository.getcongeesbyemp(id);
	}
	@Override
	public List<Long> getAlltypesIdsFromCongeeByemp(long id) {
		// TODO Auto-generated method stub
		 return congeerepository.findAllTypesIdscbyemp(id);
	}
	@Override
	public List<congee> getcongeeencours() {
		// TODO Auto-generated method stub
		 return congeerepository.congeeencours();
	}
	@Override
	public List<congee> getcongeevalide() {
		// TODO Auto-generated method stub
		 return congeerepository.congeevalide();
	}
	@Override
	public List<congee> dca() {
		
		return congeerepository.dca();
	}
	@Override
	public List<congee> dcr() {
		// TODO Auto-generated method stub
		
		return congeerepository.dcr();
	}
	@Override
	public List<congee> findByKeywordb(String Keyword) {
		// TODO Auto-generated method stub
		return congeerepository.findByKeywordb(Keyword);
	}
	@Override
	public List<congee> getcongeeinvalide() {
		// TODO Auto-generated method stub
		return congeerepository.congeeinvalide();
	}
	@Override
	public List<Long> getAllEmployeeIdsFromCongeeref() {
		// TODO Auto-generated method stub
		 return congeerepository.findAllEmployeeIdsref();
	}
	@Override
	public List<Long> getAlltypesIdsFromCongeeref() {
		// TODO Auto-generated method stub
		 return congeerepository.findAllTypesIdscref();
	}
	@Override
	public List<Long> getAllEmployeeIdsFromCongeeec() {
		// TODO Auto-generated method stub
		return congeerepository.findAllEmployeeIdsec();
	}
	@Override
	public List<Long> getAlltypesIdsFromCongeeec() {
		// TODO Auto-generated method stub
		 return congeerepository.findAllTypesIdscec();
	}
	@Override
	public List<Long> getAllEmployeeIdsFromCongeeart() {
		// TODO Auto-generated method stub
		return congeerepository.findAllEmployeeIdsart();
	}
	@Override
	public List<Long> getAlltypesIdsFromCongeeart() {
		// TODO Auto-generated method stub
		return congeerepository.findAllTypesIdscart();
	}
	

}
