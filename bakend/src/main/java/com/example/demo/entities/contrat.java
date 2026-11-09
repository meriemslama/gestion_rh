package com.example.demo.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
//le produit est une classe que est va stockee dans db
@Entity
public class contrat {
	//id est cle primaire
	@Id
	//id est generer automatique par spring : auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String dateDebut;
	private String dateFin;
	private String contenu;

	
	
	
	 @ManyToOne
	   @JsonIgnore
	   employee employee;
	@ManyToOne
	@JsonIgnore
	typecontrat typecontrat;
	

	
	
	
	

}
