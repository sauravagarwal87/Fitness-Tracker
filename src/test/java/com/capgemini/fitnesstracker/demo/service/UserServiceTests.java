package com.capgemini.fitnesstracker.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.fitnesstracker.demo.model.Admin;
import com.capgemini.fitnesstracker.demo.model.User;


@SpringBootTest
public class UserServiceTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	private static User user;

	@BeforeAll
	public static void setUp() {
		user = new User("virat123@gmail.com", "virat", "kohli", 20, 60, 170, "virat", "kohli");
	}

	@AfterAll
	public static void tearDown() {
		user = null;
	}

	@Test
	public void testRegister() {
		LOG.info("Testing user Login");
		User expected = user;
		User actual = userService.registerUser(user);
		assertEquals(expected, actual);
	}

//	@Disabled
	@Test
	public void testRegisterFailure() {
		LOG.info("Testing user Login Failure");
		User unexpected = user;
		User actual = userService.registerUser(new User("virat123@gmail.com", "virat", "kohli", 20, 60, 170, "virat", "kohli"));
		assertNotEquals(unexpected, actual);
	}
}