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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.departement;
import com.example.demo.entities.employee;
import com.example.demo.repos.deptrepository;
import com.example.demo.services.deptservice;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/departements")
public class deptcontroller {

	@Autowired
	private deptservice departementservice;
	@Autowired
	private deptrepository departementrepozitory;

	public deptcontroller(deptservice departementservice) {
		super();
		this.departementservice = departementservice;
	}

	@GetMapping("/all/{page}/{size}")
    public Page<departement> Alldepartement(@PathVariable int page , @PathVariable int size) {
    	Page<departement> depts = departementservice.getallDepartements(page, size);
        return depts;
    }
	@GetMapping("/all")
    public List<departement> Alldepartements() {
    	List<departement> depts = departementservice.allDepartements();
        return depts;
    }
    @GetMapping("/allactive")
    public List<departement> Alldepartementsactive() {
    	List<departement> depts = departementservice.allDepartementsactive();
        return depts;
    }
    @GetMapping("/alldept")
    public List<departement> getDepartments(@RequestParam String nomDept) {
        return departementrepozitory.findBynomDeptContainingIgnoreCase(nomDept);}
    @GetMapping("/allde")
    public List<String> getAlldepartements() {
    	List<String> depts = departementservice.getAllDepartments();
        return depts;
    }
	@GetMapping("/all/search/{keyword}/{page}/{size}")
	public Page<departement> findByKeyword(@PathVariable String keyword,@PathVariable int page , @PathVariable int size){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		
			return departementservice.findByKeyword(keyword, page, size);
		
		
		
		
	}
	@GetMapping("/all/search//{page}/{size}")
	public Page<departement> getallemployeesbysearchnull(@PathVariable int page , @PathVariable int size){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		return departementservice.getallDepartements(page, size);
		
		
	}

	/*@GetMapping("/departements/new")
	public String creatdepartementform(Model model){
		
		departement dept=new departement();
		model.addAttribute("departements",dept);
		return "create_departement";
		
	}*/ 
	///{id}   ,@PathVariable long id   ,id
    @PostMapping("/add")
    public ResponseEntity<Object> adddept(@RequestBody departement dept) {
    return departementservice.saveDepartement(dept);
}
 
  
 
  /* @GetMapping("/departements/edit/{id}")
	public String editdepartementform(@PathVariable Long id , Model model){
		
		model.addAttribute("departement",departementservice.getDepartement(id));
		return "edit_departement";
		
	}*/
   
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatedept(@RequestBody departement dept, @PathVariable long id) {
        return departementservice.updateDepartement(id, dept);
    }
	

    @DeleteMapping("/delete/{id}")
    public void deletedept(@PathVariable long id) {
      departementservice.deletDepartementbyid(id);
  }

  @GetMapping("/get/{id}")
  public departement finddept(@PathVariable long id) {
      return departementservice.getDepartement(id);
  }


}
