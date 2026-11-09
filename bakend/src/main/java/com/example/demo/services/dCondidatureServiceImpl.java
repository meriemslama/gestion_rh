package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.dCondidature;
import com.example.demo.repos.contratrepository;
import com.example.demo.repos.dCondidatureRepos;

@Service
public class dCondidatureServiceImpl implements dCondidatureService{
	@Autowired
	   dCondidatureRepos dCondidaturerepository;

	@Override
	public ResponseEntity<Object> saveDCondidature(dCondidature c) {
		dCondidaturerepository
        .findById(c.getId())
        .ifPresentOrElse(
                tcong -> {
                    throw new ResponseStatusException(HttpStatus.FOUND, "dCondidature ID alredy exist");
                }, () -> {
                	dCondidaturerepository.save(c);
                }
        );
		  return ResponseEntity.ok(c);
	}

	@Override
	public List<dCondidature> getallDCondidature() {
		// TODO Auto-generated method stub
		return dCondidaturerepository.findAll();
	}

	@Override
	public Page<dCondidature> getallDCondidatures(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pages=PageRequest.of(page, size);
		return dCondidaturerepository.findAll(pages);
	}

	@Override
	public void deletDCondidaturebyid(Long id) {
		dCondidaturerepository
         .findById(id)
         .ifPresentOrElse(dept -> {
        	 dCondidaturerepository.delete(dept);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dept to delete not found");
         });	
		
	}

	@Override
	public dCondidature getDCondidature(Long id) {
		// TODO Auto-generated method stub
		return  dCondidaturerepository.findById(id).get();
	}
}
