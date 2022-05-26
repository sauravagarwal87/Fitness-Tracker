package com.capgemini.fitnesstracker.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capgemini.fitnesstracker.demo.model.WeeklyActivity;


public interface IWeeklyActivityController {



	public abstract  ResponseEntity<Object> viewWeeklyActivity(String d);

	
	public abstract ResponseEntity<WeeklyActivity> removeWeeklyActivities(int weeklyId);

	public abstract ResponseEntity<Object> updateWeeklyActivities(String d1, String d2);

}
