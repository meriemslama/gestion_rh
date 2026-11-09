package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.typecongee;
import com.example.demo.entities.typecontrat;
import com.example.demo.services.typecontratservice;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/type/contrats")
public class typecontratcontroller {

	@Autowired
	private typecontratservice typecontratservice;

	public typecontratcontroller(typecontratservice typecontratservice) {
		super();
		this.typecontratservice = typecontratservice;
	}

	@GetMapping("/all")
    public List<typecontrat> Alltypecontrat() {
        List<typecontrat> tconts = typecontratservice.getalltypescontrat();
        return tconts;
    }
    @GetMapping("/all/{page}/{size}")
    public Page<typecontrat> Alltypecongee(@PathVariable int page , @PathVariable int size) {
    	Page<typecontrat> cnts = typecontratservice.getalltypecontrats(page, size);
        return cnts;
    }
	
	@GetMapping("/all/search/{keyword}/{page}/{size}")
	public Page<typecontrat> findByKeyword(@PathVariable String keyword,@PathVariable int page , @PathVariable int size){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		
			return typecontratservice.findByKeyword(keyword, page, size);
		
		
		
		
	}
	@GetMapping("/all/search//{page}/{size}")
	public Page<typecontrat> getallemployeesbysearchnull(@PathVariable int page , @PathVariable int size){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		return typecontratservice.getalltypecontrats(page, size);}
	/*@GetMapping("/departements/new")
	public String creatdepartementform(Model model){
		
		departement dept=new departement();
		model.addAttribute("departements",dept);
		return "create_departement";
		
	}*/
    @PostMapping("/add")
    public ResponseEntity<Object> addtypecontrat(@RequestBody typecontrat tcont) {
    return typecontratservice.saveTypeContrat(tcont);
}
  /* @GetMapping("/departements/edit/{id}")
	public String editdepartementform(@PathVariable Long id , Model model){
		
		model.addAttribute("departement",departementservice.getDepartement(id));
		return "edit_departement";
		
	}*/
   
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatetypecontrat(@RequestBody typecontrat tcont, @PathVariable long id) {
        return typecontratservice.updateTypeContrat(id, tcont);
    }
	

    @DeleteMapping("/delete/{id}")
    public void deletetypecontrat(@PathVariable long id) {
    	typecontratservice.delettypeContratbyid(id);
  }

  @GetMapping("/get/{id}")
  public typecontrat findtypecongee(@PathVariable long id) {
      return typecontratservice.gettypeContrat(id);
  }


}
