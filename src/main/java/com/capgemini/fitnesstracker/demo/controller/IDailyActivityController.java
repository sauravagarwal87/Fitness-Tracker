package com.capgemini.fitnesstracker.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capgemini.fitnesstracker.demo.model.Category;
import com.capgemini.fitnesstracker.demo.model.DailyActivity;



public interface IDailyActivityController {

	public abstract ResponseEntity<List<DailyActivity>> viewAllActivities();

	public abstract ResponseEntity<DailyActivity> addDailyActivities(DailyActivity activity);

	public abstract ResponseEntity<DailyActivity> updateDailyActivities(DailyActivity activity);

	public abstract    ResponseEntity<DailyActivity> removeDailyActivities(int actId);


	public abstract ResponseEntity<List<Object>> viewDailyActivity(String d);





}
