package com.cts.projectmanagement.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.projectmanagement.entities.User;
import com.cts.projectmanagement.repositories.UserRepository;



@Controller   
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired 
	private UserRepository userRepository;
// add the user
	@PostMapping(path="/add")
	public @ResponseBody User addNewUser (@RequestBody User user) {

		User userObj = new User();
		userObj.setFirstName(user.getFirstName());
		userObj.setLastName(user.getLastName());
		userObj.setEmployeeId(user.getEmployeeId());
		return userRepository.save(userObj);
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	//update user
	@PutMapping(path="/update")
	public @ResponseBody User updateUser(@RequestBody User user){
		
		User userObj = userRepository.findOne(user.getUserId());
		userObj.setEmployeeId(user.getEmployeeId());
		userObj.setFirstName(user.getFirstName());
		userObj.setLastName(user.getLastName());
		return userRepository.save(userObj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable("id") Integer userId){
	     userRepository.delete(userId);
	     return "return";
		
	}
}

