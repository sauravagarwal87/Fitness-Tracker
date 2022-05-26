package com.capgemini.fitnesstracker.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.fitnesstracker.demo.model.DailyActivity;


@SpringBootTest
public class DailyActivityServiceTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
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
	public void testAddActivity() {
		LOG.info("Testing daily Activity Login");
		DailyActivity expected = dailyActivity;
		DailyActivity actual = dailyActivityService.addActivity(dailyActivity);
		assertEquals(expected, actual);
	}

//	@Disabled
	@Test
	public void testAddActivityFailure() {
		LOG.info("Testing daily Activity Login Failure");
		DailyActivity unexpected = dailyActivity;
		DailyActivity actual = dailyActivityService.addActivity(new DailyActivity());
		assertNotEquals(unexpected, actual);
	}
}