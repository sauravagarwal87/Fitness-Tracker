package com.capgemini.fitnesstracker.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.fitnesstracker.demo.exception.InvalidDateDifferenceException;
import com.capgemini.fitnesstracker.demo.model.WeeklyActivity;
import com.capgemini.fitnesstracker.demo.model.WeeklyDiet;

public interface IWeeklyDietService {

	public abstract WeeklyDiet addWeeklyDiet(WeeklyDiet diet);

	public abstract Object updateWeeklyDiet(LocalDate date1, LocalDate date2);

	public abstract Object viewWeeklyDiet(LocalDate ld);

	public abstract WeeklyDiet removeWeeklyDiet(int weeklyDietId);

	public abstract List<Object> viewWeeklyDietFoodTypeWise(LocalDate date1, LocalDate date2) throws InvalidDateDifferenceException;

	

}
