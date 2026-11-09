package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//le produit est une classe que est va stockee dans db
@Entity
public class dCondidature {
	  @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	  
	   private long Id;
	   private String nom;
	   private String prenom;
	   private String cin;
	   private String email;
	   private String datenais; 
	   private String tel;
	   private String adresse;
	   private String ville;
	   private String cv;
	   private String lettreDeMotivation;
	   private String levelStudy;
	   private String diplome;
	   private String schollEngenniring;
	   private String yearEx;
	   private String experience;







	   
}
