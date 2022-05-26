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


@SpringBootTest
public class AdminServiceTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminService adminService;

	private static Admin admin;

	@BeforeAll
	public static void setUp() {
		admin = new Admin("msdhoni@gmail.com", "10345", "pass");
	}

	@AfterAll
	public static void tearDown() {
		admin = null;
	}

	@Test
	public void testRegister() {
		LOG.info("Testing admin Login");
		Admin expected = admin;
		Admin actual = adminService.registerAdmin(admin);
		assertEquals(expected, actual);
	}

//	@Disabled
	@Test
	public void testRegisterFailure() {
		LOG.info("Testing admin Login Failure");
		Admin unexpected = admin;
		Admin actual = adminService.registerAdmin(new Admin("msdhoni@gmail.com", "10345", "pass"));
		assertNotEquals(unexpected, actual);
	}
}