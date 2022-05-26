package com.capgemini.fitnesstracker.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.fitnesstracker.demo.model.Admin;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.service.AdminService;

/**
 * This controller class is used by admin to login, logout, register and remove
 * Also admin can view all users and delete a user
 *
 */


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:3000")
public class AdminController implements IAdminController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AdminService adminService;

	@Override
	@PostMapping("/register")
	public ResponseEntity<Admin> register(@Valid @RequestBody Admin admin) {
		LOG.info(admin.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Admin " + admin.getUserName() + " logged in successfully.");
		return new ResponseEntity<Admin>(adminService.registerAdmin(admin), headers, HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/get-all-admins")
	public ResponseEntity<List<Admin>> getAllAdmin() {
		LOG.info("get-all-Users");
		return new ResponseEntity<List<Admin>>(adminService.getAlladmin(), HttpStatus.OK);
	}

	@Override
	@PostMapping("/login")
	public ResponseEntity<Admin> login(@Valid @RequestParam(name = "Username") String username,
			@RequestParam(name = "Password") String pass) {
		LOG.info(username + "Tried to login");
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Admin " + username + " logged in successfully.");
		return new ResponseEntity<Admin>(adminService.loginAdmin(username, pass), headers, HttpStatus.OK);
	}

	@Override
	@GetMapping("/logout/{Admin}")
	public ResponseEntity<String> logout(@PathVariable(name = "Admin") String userName) {
		LOG.info(userName);
		return new ResponseEntity<String>(adminService.logoutAdmin(userName), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/delete/{Admin}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable(name = "Admin") String userName) {
		LOG.info(userName);
		return new ResponseEntity<Admin>(adminService.deleteAdmin(userName), HttpStatus.OK);

	}

	@Override
	@GetMapping("/get-all-Users")
	public ResponseEntity<List<User>> getAllUsers() {
		LOG.info("get-all-Users");
		return new ResponseEntity<List<User>>(adminService.getAllUsers(), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/unregisterUser/{user}")
	public ResponseEntity<User> deleteUser(@PathVariable(name = "user") String email) {
		LOG.info(email);
		return new ResponseEntity<User>(adminService.unregisterUser(email), HttpStatus.OK);
	}

}
