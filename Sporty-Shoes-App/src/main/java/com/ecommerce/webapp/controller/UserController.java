package com.ecommerce.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.webapp.entity.Users;
import com.ecommerce.webapp.exception.UserNotFound;
import com.ecommerce.webapp.repository.UserRepository;

@RestController

@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	 
	// GET all users
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return this.userRepository.findAll();
	}

	// GET product by id
	@GetMapping("/users/{id}")
	public Users getUsersById(@PathVariable(value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFound("User Not found with id " + userId));
	}

	// create a user
	@PostMapping("/users")
	public Users addUser(@RequestBody Users user) {
		return this.userRepository.save(user);
	}

	// update a user
	@PutMapping("/users/{id}")
	public Users updateUser(@RequestBody Users user, @PathVariable(value = "id") long userId) {
		// 1. find a user
		Users fetchedUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFound("User Not found with id " + userId));
		// 2 . set new values
		fetchedUser.setEmail(user.getEmail());
		fetchedUser.setPassword(user.getPassword());
		fetchedUser.setFirstName(user.getFirstName());
		fetchedUser.setLastName(user.getLastName());

		// 3.save a user
		return this.userRepository.save(fetchedUser);
	}

	// delete a user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable(value = "id") long userId) {
		// 1. find a user
		Users fetchedUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFound("User Not found with id " + userId));
		this.userRepository.delete(fetchedUser);
	}

}
