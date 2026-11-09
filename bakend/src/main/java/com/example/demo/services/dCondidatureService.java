package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.demo.entities.contrat;
import com.example.demo.entities.dCondidature;
import com.example.demo.entities.departement;

public interface dCondidatureService {
	ResponseEntity<Object> saveDCondidature(dCondidature c);
	 List<dCondidature>getallDCondidature();
	    Page<dCondidature>getallDCondidatures(int page ,int size);
	    void deletDCondidaturebyid(Long id);
	    public dCondidature getDCondidature(Long id);

}
