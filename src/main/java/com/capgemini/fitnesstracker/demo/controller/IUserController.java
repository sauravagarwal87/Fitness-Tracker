package com.capgemini.fitnesstracker.demo.controller;

import org.springframework.http.ResponseEntity;

import com.capgemini.fitnesstracker.demo.model.Admin;
import com.capgemini.fitnesstracker.demo.model.User;

public interface IUserController {

	public abstract ResponseEntity<User> register(User user);

	public abstract ResponseEntity<User> login(String email, String pass);

	public abstract ResponseEntity<String> logout(String userName);

	public abstract ResponseEntity<User> updateUser(User user);

}
