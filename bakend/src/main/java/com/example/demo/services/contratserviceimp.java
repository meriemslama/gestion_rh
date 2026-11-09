package com.example.demo.services;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.congee;
import com.example.demo.entities.contrat;
import com.example.demo.entities.departement;
import com.example.demo.entities.employee;
import com.example.demo.entities.typecongee;
import com.example.demo.entities.typecontrat;
import com.example.demo.repos.contratrepository;

import jakarta.transaction.Transactional;


@Service
public class contratserviceimp implements contratservice{
	String contenu;
	@Autowired
   contratrepository contratrepository;
	typecontrat t;
	typecontrat tu;
	employee emp;
	employee empu;
	departement departement;
	@Autowired
	typecontratservice type;
	@Autowired
	empservice employee;
	
	@Override
	@CrossOrigin
	@Transactional
	public ResponseEntity<Object> saveContrat(contrat c,long id,long idemp) {
		// TODO Auto-generated method stub
		t=type.gettypeContrat( id);
		emp=employee.getEmployee(idemp);
		departement=emp.getDepartement();
		this.contenu = t.getFormat();
	
	       /*this.contenu = replaceWord(this.contenu, "nomprenomemployee", emp.getNom()+' '+emp.getPrenom());
	       this.contenu = replaceWord(this.contenu, "datenaisemployee", emp.getDatenais());
	       this.contenu = replaceWord(this.contenu, "ncin", Long.toString(emp.getCin()));
	       this.contenu = replaceWord(this.contenu, "ncnss", Long.toString(emp.getCnss()));
	       this.contenu = replaceWord(this.contenu, "emailemployee", emp.getEmail());
	       this.contenu = replaceWord(this.contenu, "telemployee", Long.toString(emp.getTel()));
	       this.contenu = replaceWord(this.contenu, "entrydateemployee", emp.getDateembauche());
	       this.contenu = replaceWord(this.contenu, "posteemployee", emp.getPoste());
	       this.contenu = replaceWord(this.contenu, "salaireemployee", Long.toString(emp.getSalary()));*/
	       /*String[] motsARemplacer = {"nomprenomemployee", "datenaisemployee", "ncin","ncnss","emailemployee","telemployee","entrydateemployee","posteemployee","salaireemployee"};
	       String nouveauMot = "nouveau";

	       for (String mot : motsARemplacer) {
	           while (this.contenu.contains(mot)) {
	               texte = texte.replace(mot, nouveauMot);
	           }
	       }*/
		// Divisez le texte en un tableau de mots
		String[] words = this.contenu.split("\\s+");
		System.out.print(words);
		// Parcourez le tableau de mots et remplacez les mots correspondants
		for (int i = 0; i < words.length; i++) {
		    if (words[i].equals("nomprenomemployee")) {
		        words[i] = emp.getNom()+' '+emp.getPrenom();
		    } 
		    if (words[i].equals("Monsieur")) {
		    	if(emp.getGender()=="femme")
		           {words[i] = "Madame";}
		    	else {
		    		words[i] = "Monsieur";
		    	}
		    } 
		    if (words[i].equals("datenaisemployee")) {
		        words[i] = emp.getDatenais();
		    }
		    if (words[i].equals("\n")) {
		        words[i] = "\n";
		    }
		    if (words[i].equals("nomdept")) {
		        words[i] = departement.getNomDept();
		    }
		    if (words[i].equals("ncin")) {
		        words[i] = Long.toString(emp.getCin());
		    }
		    if (words[i].equals("emailemployee")) {
		        words[i] = emp.getEmail();
		    }
		    if (words[i].equals("ncnss")) {
		        words[i] = Long.toString(emp.getCnss());
		    }
		    if (words[i].equals("telemployee")) {
		        words[i] = Long.toString(emp.getTel());
		    }
		    if (words[i].equals("entrydateemployee")) {
		        words[i] =emp.getDateembauche();
		    }
		    if (words[i].equals("posteemployee")) {
		        words[i] =emp.getPoste();
		    }
		    if (words[i].equals("salaireemployee")) {
		        words[i] =Long.toString(emp.getSalary());
		    }
		    if (words[i].equals("Datedébut")) {
		        words[i] = c.getDateDebut();
		    }
		    if (words[i].equals("Datefin")) {
		        words[i] = c.getDateFin();
		    }
		}
		System.out.print(words);
        
		// Concaténez les mots modifiés pour former le texte final
		String modifiedText = String.join(" ", words);
		contratrepository
        .findById(c.getId())
        .ifPresentOrElse(
                dept -> {
                    throw new ResponseStatusException(HttpStatus.FOUND, "contrat ID alredy exist");
                }, () -> {
                	c.setTypecontrat(t);
                	c.setEmployee(emp);
                	c.setContenu(modifiedText);
                	contratrepository.save(c);
                }
        );
		  return ResponseEntity.ok(c);
	}
	/*public String replaceWord(String text, String wordToReplace, String replacementWord) {
	    // Vérifier si le texte est vide ou nul
	    if (text == null || text.isEmpty()) {
	        return text;
	    }

	    // Vérifier si le mot à remplacer est vide ou nul
	    if (wordToReplace == null || wordToReplace.isEmpty()) {
	        return text;
	    }

	    // Remplacer le mot
	    String newText = text.replace(wordToReplace, replacementWord);

	    // Retourner le texte modifié
	    return newText;
	}*/

	@Override
	public contrat getContrat(Long id) {
		// TODO Auto-generated method stub
		return  contratrepository.findById(id).get();
	}

	@Override
	@Transactional
	public ResponseEntity<Object> updateContrat(long id, contrat c,long idtype,long idemp) {
		// TODO Auto-generated method stub
		tu=type.gettypeContrat( idtype);
		empu=employee.getEmployee( idemp);
		this.contenu = tu.getFormat();
		String[] words = this.contenu.split("\\s+");
		System.out.print(words);
		// Parcourez le tableau de mots et remplacez les mots correspondants
		for (int i = 0; i < words.length; i++) {
		    if (words[i].equals("nomprenomemployee")) {
		        words[i] = emp.getNom()+' '+emp.getPrenom();
		    } 
		    if (words[i].equals("Monsieur")) {
		    	
		    	words[i] = "emp.getGender()";
		    } 
		    if (words[i].equals("datenaisemployee")) {
		        words[i] = emp.getDatenais();
		    }
		    if (words[i].equals("ncin")) {
		        words[i] = Long.toString(emp.getCin());
		    }
		    if (words[i].equals("\n")) {
		        words[i] ="  ";
		    }
		    if (words[i].equals("emailemployee")) {
		        words[i] = emp.getEmail();
		    }
		    if (words[i].equals("ncnss")) {
		        words[i] = Long.toString(emp.getCnss());
		    }
		    if (words[i].equals("telemployee")) {
		        words[i] = Long.toString(emp.getTel());
		    }
		    if (words[i].equals("entrydateemployee")) {
		        words[i] =emp.getDateembauche();
		    }
		    if (words[i].equals("posteemployee")) {
		        words[i] =emp.getPoste();
		    }
		    if (words[i].equals("salaireemployee")) {
		        words[i] =Long.toString(emp.getSalary());
		    }
		    if (words[i].equals("	")) {
		        words[i] = c.getDateDebut();
		    }
		    if (words[i].equals("Datefin")) {
		        words[i] = c.getDateFin();
		    }
		}
		System.out.print(words);
        
		// Concaténez les mots modifiés pour former le texte final
		String modifiedText = String.join(" ", words);
		c.setTypecontrat(tu);
		c.setEmployee(empu);
		c.setContenu(modifiedText);
		 contratrepository
         .findById(id)
         .ifPresentOrElse(cont -> {
        	 cont.setDateDebut(c.getDateDebut());
        	 cont.setDateFin(c.getDateFin());
        	// cont.setEmployees(c.getEmployees());
        	 cont.setEmployee(c.getEmployee());
        	 cont.setTypecontrat(c.getTypecontrat());
        	
        	 contratrepository.save(cont);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "contrat to update not found");
         });
		 // return ResponseEntity.accepted().body("Successfully updated acte");
		 return ResponseEntity.ok(c);	}

	@Override
	@Transactional
	


	public void deletContratbyid(Long id) {
		// TODO Auto-generated method stub
		 contratrepository
         .findById(id)
         .ifPresentOrElse(cont -> {
        	 contratrepository.delete(cont);
         }, () -> {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "contrat to delete not found");
         });	

		
	}

	

	@Override
	public List<contrat> getallContrats() {
		//List<departement>departements=new ArrayList<>();
		return contratrepository.findAll();
		// return departements;
	}

	@Override
	public Page<contrat> getallcontrats(int page ,int size) {
		//List<departement>departements=new ArrayList<>();
		Pageable pages=PageRequest.of(page, size);
		return contratrepository.findAll(pages);
		// return departements;
	}
	
	public Page<contrat> findByKeyword( String Keyword, int page, int size) {
		Pageable pages=PageRequest.of(page, size);
		return contratrepository.findByKeyword(Keyword,pages);
	}

	@Override
	public List<Long> getAllEmployeeIdsFromCongee() {
		 return contratrepository.findAllEmployeeIdsc();
	
	}

	@Override
	public List<Long> getAlltypesIdsFromCongee() {
		// TODO Auto-generated method stub
		 return contratrepository.findAllTypesIdsc();
	}

	@Override
	public List<contrat> getcontratbyemp(long id) {
		// TODO Auto-generated method stub
		 return contratrepository.getcontratssbyemp(id);
	}

	@Override
	public List<Long> getAlltypesIdsFromContratByemp(long id) {
		// TODO Auto-generated method stub
		 return contratrepository.findAllTypesIdscbyemp(id);
	}

	@Override
	public List<contrat> findByKeywordb(String Keyword) {
		// TODO Auto-generated method stub
		return contratrepository.findByKeywordb(Keyword);
	}
	

}
