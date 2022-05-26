package com.capgemini.fitnesstracker.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.capgemini.fitnesstracker.demo.model.WeeklyActivity;


public interface IWeeklyActivityService {
	

	public abstract Object updateWeeklyActivity(LocalDate date1, LocalDate date2);

	//	public abstract WeeklyActivity updateWeeklyActivity(WeeklyActivity activity);

	public abstract WeeklyActivity addWeeklyActivity(WeeklyActivity activity);

	//public abstract WeeklyActivity removeWeeklyActivity(WeeklyActivityId weeklyActivityId);

	//	public abstract List<WeeklyActivity> viewWeeklyActivity();

	//	public abstract WeeklyActivity removeWeeklyActivity(WeeklyActivityId weeklyActivityId);

	//WeeklyActivity viewWeeklyActivity(LocalDate date1, LocalDate date2);

	public abstract  Object viewWeeklyActivity(LocalDate date);

	public abstract  WeeklyActivity removeWeeklyActivity(int weeklyActivityId);

	// WeeklyActivity viewWeeklyActivity(String email, LocalDate date1, LocalDate
	// date2);

}