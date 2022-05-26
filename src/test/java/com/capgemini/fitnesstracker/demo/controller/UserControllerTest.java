package com.capgemini.fitnesstracker.demo.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;


import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.service.UserService;


@SpringBootTest
public class UserControllerTest {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserController userController;

	@MockBean
	private UserService userService;

	private static User user;

	@BeforeAll
	public static void setUp() {
		user = new User("sonu@gmail.com", "sonu", "sood", 20, 60, 170, "sonu", "sood");
	}

	@AfterAll
	public static void tearDown() {
		user = null;
	}

	@Test
	public void testLoginHttpStatus() {
		LOG.info("Testing User Login Http Status");
		when(userService.loginUser("sonu@gmail.com", "sood")).thenReturn(user);
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = userController.login("sonu@gmail.com", "sood").getStatusCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testLoginHeaders() {
		LOG.info("Testing User Login Headers");
		String expected = "[User " + user.getEmail() + " logged in successfully.]";
		when(userService.loginUser("sonu@gmail.com", "sood")).thenReturn(user);
		String actual = userController.login("sonu@gmail.com", "sood").getHeaders().get("message").toString();
		assertEquals(expected, actual);
	}
}
