package com.capgemini.fitnesstracker.demo.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.fitnesstracker.demo.exception.AdminAlreadyExistsException;
import com.capgemini.fitnesstracker.demo.exception.AdminNotFoundException;
import com.capgemini.fitnesstracker.demo.exception.NotAuthorizedException;
import com.capgemini.fitnesstracker.demo.exception.UserNotFoundException;
import com.capgemini.fitnesstracker.demo.model.Admin;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.repository.AdminRepository;
import com.capgemini.fitnesstracker.demo.repository.UserRepository;



/**
 * This is the service class of Admin Controller
 *
 */

@Service
public class AdminService implements IAdminService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;

	Admin loggedInAdmin;

	@Override
	public Admin registerAdmin(Admin admin) {
		LOG.info(admin.toString());
		Optional<Admin> userOptional = Optional.ofNullable(adminRepository.findByUserName(admin.getUserName()));
		if (userOptional.isEmpty()) {
			admin = populatedData(admin);
			return adminRepository.save(admin);
		} else {
			String exceptionMessage = "Admin with userName " + admin.getUserName() + " already exists.";
			throw new AdminAlreadyExistsException(exceptionMessage);

		}
	}

	public Admin populatedData(Admin adm) {
		Admin ad = new Admin();
		Base64.Encoder encoder = Base64.getEncoder();
		ad.setEmail(adm.getEmail());
		ad.setUserName(adm.getUserName());
		ad.setPassword(encoder.encodeToString(adm.getPassword().getBytes()));
		return ad;
	}

	@Override
	public List<Admin> getAlladmin() {
		List<Admin> userList = adminRepository.findAll();
		if (userList.isEmpty()) {
			String exceptionMessage = "Admin don't exist in the database.";
			LOG.warn(exceptionMessage);
			throw new AdminNotFoundException(exceptionMessage);

		} else {
			LOG.info("AdminList returned successfully.");
			return userList;
		}
	}

	@Override
	public Admin loginAdmin(String user, String pass) {
		LOG.info(user + "Tried to login");
		Optional<Admin> userOptional = Optional.ofNullable(adminRepository.findByUserName(user));
		Base64.Decoder decoder = Base64.getDecoder();
		if (userOptional.isPresent()) {
			if (pass.equals(new String(decoder.decode(userOptional.get().getPassword())))) {
				LOG.info(userOptional.get().toString());
				loggedInAdmin = userOptional.get();
				return loggedInAdmin;
			} else {
				String exceptionMessage = "Wrong password!";
				LOG.warn(exceptionMessage);
				throw new AdminNotFoundException(exceptionMessage);

			}
		} else {
			String exceptionMessage = "Wrong userName!";
			LOG.warn(exceptionMessage);
			throw new AdminNotFoundException(exceptionMessage);

		}
	}

	@Override
	public String logoutAdmin(String userName) {
		if (loggedInAdmin.getUserName().equals(userName)) {
			loggedInAdmin = null;
			return userName;
		} else {
			String exceptionMessage = "User with userName " + userName + " is not logged in.";
			LOG.warn(exceptionMessage);
			throw new AdminNotFoundException(exceptionMessage);

		}
	}

	public Admin deleteAdmin(String username) {
		LOG.info(username);
		Optional<Admin> empOptional = adminRepository.findById(username);
		if (empOptional.isPresent()) {
			Admin adm = empOptional.get();
			adminRepository.delete(adm);
			return adm;
		} else {
			throw new AdminNotFoundException("Employee with employeeId " + username + " not found");

		}
	}

	@Override
	public List<User> getAllUsers() {
		if (loggedInAdmin != null) {
			List<User> useList = userRepository.findAll();
			if (useList.isEmpty()) {
				String exceptionMessage = "User don't exist in the database.";
				LOG.warn(exceptionMessage);
				throw new UserNotFoundException(exceptionMessage);

			} else {
				LOG.info("UseList returned successfully.");
				return useList;
			}
		} else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);

		}
	}

	@Override
	public User unregisterUser(String email) {
		if (loggedInAdmin != null) {

			LOG.info(email);
			Optional<User> usOptional = Optional.of(userRepository.findByEmail(email));
			if (usOptional.isPresent()) {
				User user = usOptional.get();
				userRepository.delete(user);
				return user;
			} else {
				String exceptionMessage = "User with userName " + email + " not exists.";
				throw new UserNotFoundException(exceptionMessage);

			}

		} else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}

}