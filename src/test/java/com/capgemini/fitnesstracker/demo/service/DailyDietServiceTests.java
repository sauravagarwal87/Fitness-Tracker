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

import com.capgemini.fitnesstracker.demo.model.DailyDiet;
import com.capgemini.fitnesstracker.demo.model.DailyDiet.foodType;

@SpringBootTest
public class DailyDietServiceTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
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
	public void testAddDiet() {
		LOG.info("Testing add diet");
		DailyDiet expected = dailyDiet;
		DailyDiet actual = dailyDietService.addDiet(dailyDiet);
		assertEquals(expected, actual);
	}

//	@Disabled
	@Test
	public void testAddDietFailure() {
		LOG.info("Testing add diet Failure");
		DailyDiet unexpected = dailyDiet;
		DailyDiet actual = dailyDietService.addDiet(new DailyDiet());
		assertNotEquals(unexpected, actual);
	}
}