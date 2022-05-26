package com.capgemini.fitnesstracker.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.fitnesstracker.demo.model.DailyDiet;

public interface IDailyDietService {

	public abstract DailyDiet addDiet(DailyDiet diet);
	
	public abstract DailyDiet updateDiet(DailyDiet diet);

	public abstract List<Object> viewDailyDiet(LocalDate date);

	public abstract List<DailyDiet> viewDiet();

	public abstract DailyDiet removeDiet(Integer did);

}
