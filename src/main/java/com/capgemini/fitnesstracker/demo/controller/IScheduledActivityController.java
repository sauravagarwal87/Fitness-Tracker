package com.capgemini.fitnesstracker.demo.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.model.ScheduledActivity;


public interface IScheduledActivityController {

	public abstract ResponseEntity<ScheduledActivity> addScheduledActivities(ScheduledActivity activity);

	public abstract ResponseEntity<ScheduledActivity> removeScheduledActivities();

	public abstract ResponseEntity<ScheduledActivity> viewScheduledActivity();
}
