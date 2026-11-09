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

import com.example.demo.entities.User;
import com.example.demo.services.userservice;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/users")
public class usercontroller {
	@Autowired
	private userservice userservice;

	 @PutMapping("/update/status/{id}")
	    public ResponseEntity<Object> updatecongee(@RequestBody String status, @PathVariable long id) {
	        return userservice.updateStatus(id, status);
	    }

	@GetMapping("/all")
    public List<User> Allusers() {
        List<User> users = userservice.getalluser();
        return users;
    }
	
	@GetMapping("/all/{page}/{size}")
    public Page<User> Alluser(@PathVariable int page , @PathVariable int size) {
    	Page<User> user = userservice.getalluser(page, size);
        return user;
    }

	
	@GetMapping("/all/search/{keyword}/{page}/{size}")
	public Page<User> findByKeyword(@PathVariable String keyword,@PathVariable int page , @PathVariable int size){
		
			return userservice.findByKeyword(keyword, page, size);
		
		
		
		
	}
	@GetMapping("/all/search//{page}/{size}")
	public Page<User> getallemployeesbysearchnull(@PathVariable int page , @PathVariable int size){
		
		
		return userservice.getalluser(page, size);}
		

    @PostMapping("/add")
    public ResponseEntity<Object> addduser(@RequestBody User user) {
    return userservice.saveuser(user);
}

   
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateuser(@RequestBody User user, @PathVariable long id) {
        return userservice.updateuser(id, user);
    }
    


    @DeleteMapping("/delete/{id}")
    public void deleteuser(@PathVariable long id) {
    	userservice.deletuserbyid(id);
  }

  @GetMapping("/get/{id}")
  public User findcongee(@PathVariable long id) {
      return userservice.getuser(id);
  }
  @GetMapping("/email/{email}")
  public User getbyemail(@PathVariable String email) {
      return userservice.getbyEmail(email);
  }

}
