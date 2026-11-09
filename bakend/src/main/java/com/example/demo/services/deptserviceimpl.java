package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.departement;
import com.example.demo.entities.employee;
import com.example.demo.repos.deptrepository;

import jakarta.transaction.Transactional;


@Service
public class deptserviceimpl implements deptservice{

	@Autowired
   deptrepository departementrepository;
	employee employee;
	
	//com.example.employee.services.empservice empservice;
	@Override
	@CrossOrigin
	@Transactional
	//,long id
	public ResponseEntity<Object> saveDepartement(departement d) {
		//employee=empservice.getEmployee( id);
		// TODO Auto-generated method stub
		//employee=empservice.getEmployee(id);
		//d.setEmployee(employee);
		departementrepository
        .findById(d.getIdDept())
        .ifPresentOrElse(
                dept -> {
                    throw new ResponseStatusException(HttpStatus.FOUND, "dept ID alredy exist");
                }, () -> {
                	//d.setEmployee(employee);
                	departementrepository.save(d);
                }
        );
		  return ResponseEntity.ok(d);
	}
	
  
	@Override
	public departement getDepartement(Long id) {
		// TODO Auto-generated method stub
		return  departementrepository.findById(id).get();
	}

	@Override
	@Transactional
	public ResponseEntity<Object> updateDepartement(long id, departement d) {
		// TODO Auto-generated method stub
		 departementrepository
         .findById(id)
         .ifPresentOrElse(dept -> {
        	 dept.setNomDept(d.getNomDept());
        	 dept.setEmployees(d.getEmployees());
        	 dept.setStatus(d.getStatus());
        	
        	
             departementrepository.save(dept);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dept to update not found");
         });
		 // return ResponseEntity.accepted().body("Successfully updated acte");
		 return ResponseEntity.ok(d);	}

	@Override
	@Transactional
	


	public void deletDepartementbyid(Long id) {
		// TODO Auto-generated method stub
		 departementrepository
         .findById(id)
         .ifPresentOrElse(dept -> {
        	 departementrepository.delete(dept);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dept to delete not found");
         });	

		
	}

	

	@Override
	public Page<departement> getallDepartements(int page ,int size) {
		//List<departement>departements=new ArrayList<>();
		Pageable pages=PageRequest.of(page, size);
		return departementrepository.findAll(pages);
		// return departements;
	}
	public List<departement> allDepartements() {
	
		return departementrepository.findAll();
		// return departements;
	}
	public Page<departement> findByKeyword( String Keyword, int page, int size) {
		Pageable pages=PageRequest.of(page, size);
		return departementrepository.findByKeyword(Keyword,pages);
	}
	 public List<String> getAllDepartments() {
	        List<departement> departments = departementrepository.findAll();
	        return departments.stream().map(departement::getNomDept).collect(Collectors.toList());
	    }


	@Override
	public List<departement> allDepartementsactive() {
		// TODO Auto-generated method stub
		return departementrepository.deptacytive();
	}




	

}
