package com.capgemini.fitnesstracker.demo.service;

import com.capgemini.fitnesstracker.demo.model.ScheduledDiet;

public interface IScheduledDietService {

	public abstract ScheduledDiet updateDiet(ScheduledDiet diet);
	
	public abstract ScheduledDiet removeDiet();
	
	public abstract ScheduledDiet viewDiet();

}
