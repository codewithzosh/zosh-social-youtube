package com.zosh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.models.User;
import com.zosh.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		User savedUser=userRepository.save(newUser);
		
		return savedUser;
	}
	
	
	
	@GetMapping("/users")
	public List<User> getUsers() {
		
		List<User> users=userRepository.findAll();
		
		return users;
		
	}
	
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new Exception("user not exist with userid " + id);
	}
	

	
	@PutMapping("/users/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
		
		Optional<User> user1 = userRepository.findById(userId);
		
		if(user1.isEmpty()) {
			throw new Exception("user not exit with id "+userId);
		}
		
         User oldUser= user1.get();
         
         if(user.getFirstName()!=null) {
        	 oldUser.setFirstName(user.getFirstName());
         }
         if(user.getLastName()!=null) {
        	 oldUser.setLastName(user.getLastName());
         }
         if(user.getEmail()!=null) {
        	 oldUser.setEmail(user.getEmail());
         }
         
         User updatedUser=userRepository.save(oldUser);
		
         return updatedUser;
         
	}
	
	@DeleteMapping("users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {

		Optional<User> user = userRepository.findById(userId);
		
		if(user.isEmpty()) {
			throw new Exception("user not exit with id "+userId);
		}
		
		userRepository.delete(user.get());
		
		return "user deleted successfully with id "+userId;
	}

}
