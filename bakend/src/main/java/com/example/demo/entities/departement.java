package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
//le produit est une classe que est va stockee dans db
@Entity
public class departement {
	//id est cle primaire
	@Id
	//id est generer automatique par spring : auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDept;
	private String nomDept;
	private String status;
	
	@OneToMany(mappedBy = "departement")
	
	Collection <employee> employees =new ArrayList <> ();
	

	/*@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emprespid", nullable = false)
    private employee user;*/
	@OneToOne(cascade = CascadeType.ALL)
	  @JsonIgnore
	@JoinColumn(name = "respid",referencedColumnName="id")
	private employee employee;
	
	
	
	

}
