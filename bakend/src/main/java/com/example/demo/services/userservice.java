package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.User;

public interface userservice {
	ResponseEntity<Object> saveuser(User user);
	
	  
    void deletuserbyid(Long id);
    public User getuser(Long id);
    List<User>getalluser();
    Page<User>getalluser(int page ,int size);
    public User getbyEmail(String email);
    Page<User>findByKeyword(String Keyword,int page ,int size);
	ResponseEntity<Object> updateuser(long id, User user);
	ResponseEntity<Object> updateStatus(long id, String status);

}
