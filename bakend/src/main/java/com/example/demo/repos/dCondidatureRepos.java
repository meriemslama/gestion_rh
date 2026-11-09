package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.entities.dCondidature;

@Repository
public interface dCondidatureRepos extends JpaRepository<dCondidature, Long>{

}
