package com.capgemini.fitnesstracker.demo.service;

import java.util.List;

import com.capgemini.fitnesstracker.demo.model.ScheduledActivity;


public interface IScheduledActivityService {
	
	public abstract ScheduledActivity updateActivity(ScheduledActivity activity);
	
	public abstract ScheduledActivity removeActivity();
	
	public abstract ScheduledActivity viewActivity();

}
