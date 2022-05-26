package com.capgemini.fitnesstracker.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.fitnesstracker.demo.model.Category;
import com.capgemini.fitnesstracker.demo.model.DailyActivity;




public interface IDailyActivityService {

	public abstract DailyActivity addActivity(DailyActivity activity);
	
	public abstract DailyActivity updateActivity(DailyActivity activity);

	public abstract DailyActivity removeActivity(Integer aid);

	public abstract List<Object> viewDailyActivity(LocalDate date);


}
