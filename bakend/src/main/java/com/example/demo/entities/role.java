package com.example.demo.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

//le produit est une classe que est va stockee dans db
@Entity
public class role {
	//id est cle primaire
		@Id
		//id est generer automatique par spring : auto increment
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long idt;
		private String role;
		private String description;

}
