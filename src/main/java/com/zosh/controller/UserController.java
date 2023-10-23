package com.zosh.controller;

import java.util.ArrayList;
import java.util.List;

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
		
		List<User> users=new ArrayList<>();
		
		User user1=new User(1,"code","zosh","codewithzosh@gmail.com","12345");
		User user2=new User(2,"raam","arora","raam@gmail.com","12345");
		
		users.add(user1);
		users.add(user2);
		
		return users;
	}
	
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) {
		
		User user1=new User(1,"code","zosh","codewithzosh@gmail.com","12345");
		user1.setId(id);
		
		return user1;
	}
	

	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		
		User user1=new User(1,"code","zosh","codewithzosh@gmail.com","12345");
		
		if(user.getFirstName()!=null) {
			user1.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			user1.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			user1.setEmail(user.getEmail());
		}
		
		return user1;
	}
	
	@DeleteMapping("users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		
		return "user deleted successfully with id "+userId;
	}

}
