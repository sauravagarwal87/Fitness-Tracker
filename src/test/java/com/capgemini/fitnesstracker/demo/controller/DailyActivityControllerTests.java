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

import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.service.DailyActivityService;

@SpringBootTest
public class DailyActivityControllerTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DailyActivityController dailyActivityController;

	@MockBean
	private DailyActivityService dailyActivityService;

	private static DailyActivity dailyActivity;

	@BeforeAll
	public static void setUp() {
		dailyActivity = new DailyActivity();
	}

	@AfterAll
	public static void tearDown() {
		dailyActivity = null;
	}

	@Test
	public void testLoginHttpStatus() {
		LOG.info("Testing Daily Activity Controller Http Status");
		when(dailyActivityService.addActivity(dailyActivity)).thenReturn(dailyActivity);
		HttpStatus expected = HttpStatus.CREATED;
		HttpStatus actual = dailyActivityController.addDailyActivities(dailyActivity).getStatusCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testLoginHeaders() {
		LOG.info("Testing Daily Activity Controller Headers");
		String expected = "[Activity was created successfully.]";
		when(dailyActivityService.addActivity(dailyActivity)).thenReturn(dailyActivity);
		String actual = dailyActivityController.addDailyActivities(dailyActivity).getHeaders().get("message").toString();
		assertEquals(expected, actual);
	}
}
