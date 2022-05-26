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

import com.capgemini.fitnesstracker.demo.model.Admin;
import com.capgemini.fitnesstracker.demo.service.AdminService;



@SpringBootTest
public class AdminControllerTest {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminController adminController;

	@MockBean
	private AdminService adminService;

	private static Admin admin;

	@BeforeAll
	public static void setUp() {
		admin = new Admin("shweta@gmail.com", "shweta", "bharti");
	}

	@AfterAll
	public static void tearDown() {
		admin = null;
	}

	@Test
	public void testLoginHttpStatus() {
		LOG.info("Testing Admin Register HttpStatus");
		when(adminService.registerAdmin(admin)).thenReturn(admin);
		HttpStatus expected = HttpStatus.CREATED;
		HttpStatus actual = adminController.register(admin).getStatusCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testLoginHeaders() {
		LOG.info("Testing Admin Register Headers");
		String expected = "[Admin " + admin.getUserName() + " logged in successfully.]";
		when(adminService.registerAdmin(admin)).thenReturn(admin);
		String actual = adminController.register(admin).getHeaders().get("message").toString();
		assertEquals(expected, actual);
	}
}
