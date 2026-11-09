package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class typecongee {
	  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  
   private long Id;
   private String type;
   
   
	@OneToMany(mappedBy = "typecongee")
	Collection <congee> congee =new ArrayList <> ();


   








   
}
