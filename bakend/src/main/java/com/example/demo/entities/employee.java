package com.example.demo.entities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class employee {
	  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  
   private long Id;
   private String nom;
   private String prenom;
   private long cin;
   private String poste;
   private String email;
   private String datenais; 
   private long tel;
   private String adresse;
   private String ville;
   private long cnss;
   private String dateembauche;
   private long salary;
   private String gender;
   private String image;
   private String qrimg;
   //private int numdept;
   @ManyToOne
   @JsonIgnore
   departement departement;
   @OneToMany(mappedBy = "employee")
	Collection <congee> congees =new ArrayList <> ();
	@OneToMany(mappedBy = "employee")
	Collection <contrat> contrats =new ArrayList <> ();
   @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY,
           cascade = CascadeType.ALL)
   //@JsonIgnore
   private departement dept;







   
}
