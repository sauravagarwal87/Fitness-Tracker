package com.capgemini.fitnesstracker.demo.service;

import java.util.List;

import com.capgemini.fitnesstracker.demo.model.Admin;
import com.capgemini.fitnesstracker.demo.model.User;

public interface IAdminService {

	public abstract List<Admin> getAlladmin();

	public abstract Admin loginAdmin(String username, String pass);

	public abstract String logoutAdmin(String userName);

	public abstract Admin deleteAdmin(String username);

	public abstract Admin registerAdmin(Admin admin);

	public abstract List<User> getAllUsers();

	public abstract User unregisterUser(String email);

}
