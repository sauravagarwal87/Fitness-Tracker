package com.capgemini.fitnesstracker.demo.service;

//import java.util.List;
import java.util.Optional;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.capgemini.fitnesstracker.demo.exception.UserAlreadyExistsException;
import com.capgemini.fitnesstracker.demo.exception.UserNotFoundException;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.repository.UserRepository;

/**
 * This is the service class of User Controller
 *
 */

@Service
public class UserService implements IUserService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	User loggedInUser;

	@Override
	public User registerUser(User user) {
		LOG.info(user.toString());

		Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));

		if (userOptional.isEmpty()) {
			user = populatedData(user);
			return userRepository.save(user);
		} else {
			String exceptionMessage = "User with userName " + user.getEmail() + "already exists.";
			throw new UserAlreadyExistsException(exceptionMessage);
		}

	}

	public User populatedData(User user) {
		Base64.Encoder encoder = Base64.getEncoder();
		User us = new User();
		us.setEmail(user.getEmail());
		us.setAge(user.getAge());
		us.setFirstName(user.getFirstName());
		us.setLastName(user.getLastName());
		us.setHeight(user.getHeight());
		us.setWeight(user.getWeight());
		us.setUserName(user.getUserName());
		us.setPassword(encoder.encodeToString(user.getPassword().getBytes()));
		return us;
	}

	@Override
	public User loginUser(String email, String pass) {
		LOG.info(email + "tried to login");
		Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
		Base64.Decoder decoder = Base64.getDecoder();
		if (userOptional.isPresent()) {
			if (pass.equals(new String(decoder.decode(userOptional.get().getPassword())))) {
				LOG.info(userOptional.get().toString());
				loggedInUser = userOptional.get(); // successful login
				return loggedInUser;
			} else {
				String exceptionMessage = "Wrong password!";
				LOG.warn(exceptionMessage);
				throw new UserNotFoundException(exceptionMessage);

			}
		} else {
			String exceptionMessage = "Wrong userName!";
			LOG.warn(exceptionMessage);
			throw new UserNotFoundException(exceptionMessage);
		}
	}

	@Override
	public String logoutUser(String email) {
		if (loggedInUser.getEmail().equals(email)) {
			loggedInUser = null;
			return email;
		} else {
			String exceptionMessage = "User with email " + email + " is not logged in.";
			LOG.warn(exceptionMessage);
			throw new UserNotFoundException(exceptionMessage);

		}
	}

	@Override
	public User updateUser(User user) {
		Optional<User> userOptional = Optional.of(userRepository.findByEmail(user.getEmail()));
		if (userOptional.isPresent()) {
			LOG.info(userOptional.get().toString());
			return userRepository.save(user);
		} else {
			String exceptionMessage = "user with userName " + user.getEmail() + " not found!";
			LOG.warn(exceptionMessage);
			// throw new AppUserNotFoundException(exceptionMessage);
			return null;
		}
	}

	@Override
	public User unregisterUser(String email) {
		LOG.info(email);
		Optional<User> usOptional = Optional.of(userRepository.findByEmail(email));
		if (usOptional.isPresent()) {
			User user = usOptional.get();
			userRepository.delete(user);
			return user;
		} else {
			throw new UserNotFoundException("user with email " + email + " not found");
		}
	}
}
