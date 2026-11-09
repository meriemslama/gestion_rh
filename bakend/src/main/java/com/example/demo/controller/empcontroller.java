package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.example.demo.Qrcodegenerator;
import com.example.demo.entities.employee;
import com.example.demo.repos.emprepository;

import com.example.demo.services.empservice;
import com.google.zxing.WriterException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@CrossOrigin
@RestController
@RequestMapping("/employees")
public class empcontroller {
	@Autowired
	private empservice employeeservice;
	employee employee;
	@Autowired
	private emprepository employeerepository;
	public empcontroller(empservice employeeservice) {
		super();
		this.employeeservice = employeeservice;
	}
	
	@GetMapping("/all/{page}/{size}")
	public Page<employee> getallemployees(@PathVariable int page , @PathVariable int size){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		return employeeservice.getallEmployees(page, size);
		
		
	}
	@GetMapping("/all")
	public List<employee> getallemployees(){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		return employeeservice.allEmployees();
		
		
	}
	@GetMapping("/femmes")
	public int femmesemployees(){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		return employeeservice.femmeEmployee();
		
		
	}
	@GetMapping("/hommes")
	public int hommesemployees(){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		return employeeservice.hommeEmployee();
		
		
	}

	@GetMapping("/all/search/{keyword}/{page}/{size}")
	public Page<employee> findByKeyword(@PathVariable String keyword,@PathVariable int page , @PathVariable int size){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		
			return employeeservice.findByKeyword(keyword, page, size);
		
		
		
		
	}
	 @GetMapping("/qrcode/{id}")
	    public ResponseEntity<byte[]> getQrCodeImage(@PathVariable Long id) {
	    	employee=this.employeeservice.getEmployee(id);
	        // Récupérer les données de l'image QR Code depuis la base de données
	        byte[] qrCodeData = employee.getQrimg().getBytes();
	        
	        // Créer les en-têtes HTTP pour les données de l'image
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_PNG);
	        
	        // Retourner les données de l'image avec les en-têtes HTTP
	        return new ResponseEntity<>(qrCodeData, headers, HttpStatus.OK);
	    }
	@GetMapping("/all/search//{page}/{size}")
	public Page<employee> getallemployeesbysearchnull(@PathVariable int page , @PathVariable int size){
		//return produitservice.getallproduits();
		//model.addAttribute("listEmployees",employeeservice.getallEmployees());
		
		return employeeservice.getallEmployees(page, size);
		
		
	}

   @PostMapping("/add/{id}")
  
   public ResponseEntity<Object> addemp(@RequestBody employee emp,@PathVariable long id) throws WriterException, IOException {
	   Qrcodegenerator.generateQrCode(emp);
	  
       return employeeservice.saveEmployee(emp,id);
	}
  /* @GetMapping("/employees/edit/{id}")
	public String editemployeeform(@PathVariable Long id , Model model){
		
		model.addAttribute("employee",employeeservice.getEmployee(id));
		return "edit_employee";
		
	}*/
   
   @PutMapping("/update/{id}/{iddept}")
   public ResponseEntity<Object> updateemp(@RequestBody employee emp, @PathVariable long id, @PathVariable long iddept) throws WriterException, IOException {
	   File imageFile = new File("C:\\Users\\pc\\Desktop\\rh\\gestionRH\\src\\assets\\images\\qrcode\\"+emp.getNom()+emp.getCin()+"-QRCODE.png");
	    if (imageFile.exists()) {
	        // Supprime l'image existante
	        imageFile.delete();
	    }
	   Qrcodegenerator.generateQrCode(emp);
       return employeeservice.updateEmployee(id, emp,iddept);
	}
	

   @DeleteMapping("/delete/{id}")
   public void deleteemp(@PathVariable long id) {
     employeeservice.deletEmployeebyid(id);
 }
   
   @GetMapping("/get/{id}")
   public employee findActe(@PathVariable long id) {
       return employeeservice.getEmployee(id);
   }
   @GetMapping("/find/{email}")
   public employee findByEmail(@PathVariable String email) {
       return employeeservice.findByEmail(email);
   }
   
 
   
   

}


