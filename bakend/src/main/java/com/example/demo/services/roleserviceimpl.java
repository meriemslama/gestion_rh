package com.example.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.role;
import com.example.demo.entities.typecongee;
import com.example.demo.repos.rolerepos;
import com.example.demo.repos.typecongeerepository;
//import com.example.demo.user.user;

import jakarta.transaction.Transactional;

@Service
public class roleserviceimpl implements roleservice{

	@Autowired
	rolerepos rolerepository;
	//@Autowired
	//com.example.demo.user.userrepository userrepository;
		@Override
		@CrossOrigin
		@Transactional
		public ResponseEntity<Object> saverole(role role) {
		
			rolerepository
	        .findById(role.getIdt())
	        .ifPresentOrElse(
	                tcong -> {
	                    throw new ResponseStatusException(HttpStatus.FOUND, "role ID alredy exist");
	                }, () -> {
	                	rolerepository.save(role);
	                }
	        );
			  return ResponseEntity.ok(role);
		}
		@Override
		public role getrole(Long id) {
			
			return  rolerepository.findById(id).get();
		}

		@Override
		@Transactional
		public ResponseEntity<Object> updaterole(long id, role role) {
			
			rolerepository
	         .findById(id)
	         .ifPresentOrElse(rolet -> {
	        	 rolet.setRole(role.getRole());
	        	 rolet.setDescription(role.getDescription());
	        	
	     
	        	
	        	 rolerepository.save(rolet);
	         }, () -> {
	             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role to update not found");
	         });
			
			 return ResponseEntity.ok(role);	}

		@Override
		@Transactional
		


		public void deletrolebyid(Long id) {
	
			rolerepository
	         .findById(id)
	         .ifPresentOrElse(role -> {
	        	 rolerepository.delete(role);
	         }, () -> {
	             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role to delete not found");
	         });	

			
		}

		

		@Override
		public List<role> getallrole() {
			
			return rolerepository.findAll();
			
		}
		public Page<role> getallrole(int page ,int size) {
		
			Pageable pages=PageRequest.of(page, size);
			return rolerepository.findAll(pages);
			
		}
		
		public Page<role> findByKeyword( String Keyword, int page, int size) {
			Pageable pages=PageRequest.of(page, size);
			return rolerepository.findByKeyword(Keyword,pages);
		}
	
		
		/*@Override
		public void assignUser(long userid, long roleid) {
			user user=userrepository.getById(userid);
			role role=rolerepository.getById(roleid);
			Set<role>userroles=user.getRoles();
			userroles.add(role);
			user.setRoles(userroles);
			userrepository.save(user);
			
		}
		@Override
		public void unassignUser(long userid, long roleid) {
			user user=userrepository.getById(userid);
			user.getRoles().removeIf(x->x.getIdt()==roleid);
			userrepository.save(user);
		}
		@Override
		public Set<role> getUserRole(user user) {
			return user.getRoles();
		}
		@Override
		public List<role> getUserNotRole(user user) {
		
			return rolerepository.getNotRoles(user.getIdt());
		}
		*/
}
