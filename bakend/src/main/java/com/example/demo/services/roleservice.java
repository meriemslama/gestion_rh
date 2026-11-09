package com.example.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.role;
//import com.example.demo.user.user;

public interface roleservice {
	ResponseEntity<Object> saverole(role role);
	
	  
    void deletrolebyid(Long id);
    public role getrole(Long id);
    List<role>getallrole();
    Page<role>getallrole(int page ,int size);
	  
    Page<role>findByKeyword(String Keyword,int page ,int size);
	ResponseEntity<Object> updaterole(long id, role role);
	//public void assignUser(long userid,long roleid);
	//public void unassignUser(long userid,long roleid);
	//public Set<role> getUserRole(user user);
	//public List<role> getUserNotRole(user user);
}
