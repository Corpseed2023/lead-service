package com.lead.dashboard.controller.userController;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("api/v1/getAllUser")
	public ResponseEntity<List<User>> getAllUserData()
	{

		List<User> allUser=userService.getAllUsers();

		if(!allUser.isEmpty())
		{
			return  new ResponseEntity<>(allUser,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@PostMapping("api/v1/createUsser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("api/v1/getUser")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userService.getUserById(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("api/v1/updateUser")
	public ResponseEntity<User> updateUser(@RequestParam Long id, @RequestBody User user) {
		User existingUser = userService.getUserById(id);
		if (existingUser != null) {
			user.setId(id);
			User updatedUser = userService.updateUser(user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("api/v1/deleteUser")
	public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
		User existingUser = userService.getUserById(id);
		if (existingUser != null) {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
