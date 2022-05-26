package com.capgemini.fitnesstracker.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capgemini.fitnesstracker.demo.model.Admin;
import com.capgemini.fitnesstracker.demo.model.User;

public interface IAdminController {

	public abstract ResponseEntity<Admin> login(String username, String pass);

	public abstract ResponseEntity<String> logout(String userName);

	public abstract ResponseEntity<List<Admin>> getAllAdmin();

	public abstract ResponseEntity<Admin> deleteAdmin(String userName);

	public abstract ResponseEntity<Admin> register(Admin admin);

	public abstract ResponseEntity<List<User>> getAllUsers();

	public abstract ResponseEntity<User> deleteUser(String email);

}
