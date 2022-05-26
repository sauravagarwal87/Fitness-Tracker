package com.capgemini.fitnesstracker.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.fitnesstracker.demo.model.Admin;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.service.UserService;

/**
 * This controller class is used by user to login, logout, register and delete itself
 *
 *
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:3000")
public class UserController implements IUserController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@Override
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Valid User user) {
		LOG.info(user.toString());
		return new ResponseEntity<User>(userService.registerUser(user), HttpStatus.CREATED);
	}

	@Override
	@PostMapping("/login")
	public ResponseEntity<User> login(@Valid @RequestParam(name = "Email") String email, @RequestParam(name = "Password") String pass) {
		LOG.info(email + "Tried to login");
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "User " + email + " logged in successfully.");
		return new ResponseEntity<User>(userService.loginUser(email, pass), headers, HttpStatus.OK);
	}

	@Override
	@GetMapping("/logout/{user}")
	public ResponseEntity<String> logout(@PathVariable(name = "user") String userName) {
		LOG.info(userName);
		return new ResponseEntity<String>(userService.logoutUser(userName), HttpStatus.OK);
	}

	@Override
	@PutMapping("/update-user")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
		LOG.info(user.toString());
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}

}
