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

import com.example.demo.entities.role;
import com.example.demo.services.roleservice;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/roles")
public class rolecontoller {
	@Autowired
	private roleservice roleservice;

	public rolecontoller(roleservice roleservice) {
		super();
		this.roleservice = roleservice;
	}

	@GetMapping("/all")
    public List<role> Allroles() {
        List<role> roles = roleservice.getallrole();
        return roles;
    }
	
	@GetMapping("/all/{page}/{size}")
    public Page<role> Allrole(@PathVariable int page , @PathVariable int size) {
    	Page<role> role = roleservice.getallrole(page, size);
        return role;
    }

	
	@GetMapping("/all/search/{keyword}/{page}/{size}")
	public Page<role> findByKeyword(@PathVariable String keyword,@PathVariable int page , @PathVariable int size){
		
			return roleservice.findByKeyword(keyword, page, size);
		
		
		
		
	}
	@GetMapping("/all/search//{page}/{size}")
	public Page<role> getallemployeesbysearchnull(@PathVariable int page , @PathVariable int size){
		
		
		return roleservice.getallrole(page, size);}
		

    @PostMapping("/add")
    public ResponseEntity<Object> adddrole(@RequestBody role role) {
    return roleservice.saverole(role);
}

   
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updaterole(@RequestBody role role, @PathVariable long id) {
        return roleservice.updaterole(id, role);
    }


    @DeleteMapping("/delete/{id}")
    public void deleterole(@PathVariable long id) {
    	roleservice.deletrolebyid(id);
  }

  @GetMapping("/get/{id}")
  public role findcongee(@PathVariable long id) {
      return roleservice.getrole(id);
  }



}
