package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
//le produit est une classe que est va stockee dans db
@Entity
public class typecontrat {
	//id est cle primaire
	@Id
	//id est generer automatique par spring : auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String type;
	private String format;
	@OneToMany(mappedBy = "typecontrat")
	Collection <contrat> contrat =new ArrayList <> ();
	

	
	
	
	

}
