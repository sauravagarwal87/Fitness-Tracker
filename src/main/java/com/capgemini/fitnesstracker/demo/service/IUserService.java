package com.capgemini.fitnesstracker.demo.service;

import java.util.List;

import com.capgemini.fitnesstracker.demo.model.User;

public interface IUserService {
	
	public abstract User registerUser(User user);

	public abstract User loginUser(String email, String pass);

	public abstract String logoutUser(String email);

	public abstract User updateUser(User user);
	
	public abstract User unregisterUser(String email);


}
