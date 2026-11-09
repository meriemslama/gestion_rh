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

import com.example.demo.services.userservice;
import com.example.demo.entities.User;
import com.example.demo.entities.congee;
import com.example.demo.repos.userrepository;

import jakarta.transaction.Transactional;

@Service
public class userserviceimpl implements userservice{

	@Autowired
	userrepository userrepository;
		@Override
		@CrossOrigin
		@Transactional
		public ResponseEntity<Object> saveuser(User user) {
		
			userrepository
	        .findById(user.getId())
	        .ifPresentOrElse(
	                tcong -> {
	                    throw new ResponseStatusException(HttpStatus.FOUND, "user ID alredy exist");
	                }, () -> {
	                	userrepository.save(user);
	                }
	        );
			  return ResponseEntity.ok(user);
		}
		@Override
		public User getuser(Long id) {
			
			return  userrepository.findById(id).get();
		}
		@Override
		@Transactional
		public ResponseEntity<Object> updateStatus(long id, String status) {
			// TODO Auto-generated method stub
			User congee=this.getuser(id);
			userrepository
	         .findById(id)
	         .ifPresentOrElse(cong -> {
	        	 cong.setEtat(status);
	        	
	        	
	        	 userrepository.save(cong);
	         }, () -> {
	             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "congee to update not found");
	         });
			 // return ResponseEntity.accepted().body("Successfully updated acte");
			 return ResponseEntity.ok(congee);	}


		@Override
		@Transactional
		public ResponseEntity<Object> updateuser(long id, User user) {
			
			userrepository
	         .findById(id)
	         .ifPresentOrElse(usert -> {
	        
	        	 usert.setPassword(user.getPassword());
	        	 usert.setEmail(user.getEmail());
	        	 usert.setFirstName(user.getFirstName());
	        	 usert.setLastName(user.getLastName());
	        	 usert.setRole(user.getRole());
	        	 //usert.setEmployee(user.getEmployee());
	        	
	        	 userrepository.save(usert);
	         }, () -> {
	             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user to update not found");
	         });
			
			 return ResponseEntity.ok(user);	}

		@Override
		@Transactional
		


		public void deletuserbyid(Long id) {
	
			userrepository
	         .findById(id)
	         .ifPresentOrElse(user -> {
	        	 userrepository.delete(user);
	         }, () -> {
	             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user to delete not found");
	         });	

			
		}

		

		@Override
		public List<User> getalluser() {
			
			return userrepository.findAll();
			
		}
		public Page<User> getalluser(int page ,int size) {
		
			Pageable pages=PageRequest.of(page, size);
			return userrepository.findAll(pages);
		
		}
		
		public Page<User> findByKeyword( String Keyword, int page, int size) {
			Pageable pages=PageRequest.of(page, size);
			return userrepository.findByKeyword(Keyword,pages);
		}
		@Override
		public User getbyEmail(String email) {
			// TODO Auto-generated method stub
			return  userrepository.findByEmail(email).get();
		}
		

}
