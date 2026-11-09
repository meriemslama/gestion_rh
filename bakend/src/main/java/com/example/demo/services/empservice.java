package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.employee;





public interface empservice  {
	ResponseEntity<Object> saveEmployee(employee e , long  id);
	
	public int femmeEmployee();
	public int hommeEmployee();
	    void deletEmployeebyid(Long id);
	    public employee getEmployee(Long id);
	    Page<employee>getallEmployees(int page ,int size);
	    List<employee>allEmployees();
	    Page<employee>findByKeyword(String Keyword,int page ,int size);
		ResponseEntity<Object> updateEmployee(long id, employee newemp,long iddept);
		public employee findByEmail(String email);
}
