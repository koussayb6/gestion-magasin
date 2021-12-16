package com.esprit.springjwt.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.springjwt.models.Payload;
import com.esprit.springjwt.models.User;
import com.esprit.springjwt.security.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController{
	
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl ;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	 @GetMapping("/User/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
	        return new ResponseEntity<>(userDetailsServiceImpl.getUserById(id), HttpStatus.OK);
	        
	   }
	 
	 
	 
	 @GetMapping("/Users")
		public List <User> ShowUsers(){
			return userDetailsServiceImpl.getAllUsers(); 
		}
	 @GetMapping("/Users/{username}") 
	 public List<User> FindByName(@PathVariable("username") String username){
		 
		 return userDetailsServiceImpl.FindAllByUsername(username);
	 }
	 
	 
	 
//	@GetMapping("/users")  
//	private List<User> getAllUser()   
//	{  
//	return userDetailsServiceImpl.getAllUser();  
//	}  
	
	@DeleteMapping({"/deleteUser/{id}"})
	// @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
		userDetailsServiceImpl.DeleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	 @PutMapping({"/edit/{id}"})
	    public  ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
	        userDetailsServiceImpl.UpdateUser(id, user);
	        return new ResponseEntity<>(userDetailsServiceImpl.getUserById(id), HttpStatus.OK);
	    }
	 
	
	 @PutMapping({"/editByAdmin/{id}"})
	// @PreAuthorize("hasRole('Admin)")
	    public  ResponseEntity<User> updateUserAdmin(@PathVariable("id") Long id, @RequestBody Payload payload) {
	        userDetailsServiceImpl.UpdateUserAdmin(id, payload.role);
	        return new ResponseEntity<>(userDetailsServiceImpl.getUserById(id), HttpStatus.OK);
	    }
}
