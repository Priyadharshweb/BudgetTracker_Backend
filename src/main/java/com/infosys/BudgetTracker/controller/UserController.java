package com.infosys.BudgetTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.BudgetTracker.entity.User;
import com.infosys.BudgetTracker.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userSer;

	@GetMapping
	public List<User> getAllTask() {
		return userSer.fetchUser();

	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) {
		return userSer.findById(id); 
	}

	@PostMapping
	public User createTask(@RequestBody User data) {
		return userSer.creatingUser(data);
	}


	@DeleteMapping("/{id}")
	public String deleteTask(@PathVariable long id) {
		return userSer.deletingUser(id);
	}

	@PutMapping("/{id}")
	public String updateTask(@PathVariable long id, @RequestBody User data2) {
		return userSer.updatingUser(id, data2);
	}


}
