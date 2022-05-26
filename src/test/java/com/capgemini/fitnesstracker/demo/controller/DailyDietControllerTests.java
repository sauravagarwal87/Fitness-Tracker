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

import com.capgemini.fitnesstracker.demo.model.DailyDiet;
import com.capgemini.fitnesstracker.demo.service.DailyDietService;



@SpringBootTest
public class DailyDietControllerTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DailyDietController dailyDietController;

	@MockBean
	private DailyDietService dailyDietService;

	private static DailyDiet dailyDiet;

	@BeforeAll
	public static void setUp() {
		dailyDiet = new DailyDiet();
	}

	@AfterAll
	public static void tearDown() {
		dailyDiet = null;
	}

	@Test
	public void testLoginHttpStatus() {
		LOG.info("Testing Daily Diet Controller Http Status");
		when(dailyDietService.addDiet(dailyDiet)).thenReturn(dailyDiet);
		HttpStatus expected = HttpStatus.CREATED;
		HttpStatus actual = dailyDietController.addDailyDiets(dailyDiet).getStatusCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testLoginHeaders() {
		LOG.info("Testing Daily Diet Controller Headers");
		String expected = "[Diet was added successfully.]";
		when(dailyDietService.addDiet(dailyDiet)).thenReturn(dailyDiet);
		String actual = dailyDietController.addDailyDiets(dailyDiet).getHeaders().get("message").toString();
		assertEquals(expected, actual);
	}
}
