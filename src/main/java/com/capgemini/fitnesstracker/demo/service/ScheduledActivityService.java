package com.capgemini.fitnesstracker.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.fitnesstracker.demo.exception.NotAuthorizedException;
import com.capgemini.fitnesstracker.demo.exception.UserNotFoundException;
import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.model.ScheduledActivity;

import com.capgemini.fitnesstracker.demo.repository.ScheduledActivityRepository;

/**
 * This is the service class of Scheduled Activity Controller
 *
 */

@Service
public class ScheduledActivityService implements IScheduledActivityService{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ScheduledActivity activity;
	
	@Autowired
	private ScheduledActivityRepository repository;
	
	@Autowired
	private UserService usService;
	
	@Override
	public ScheduledActivity updateActivity(ScheduledActivity activity2) {
		LOG.info("Add Scheduled Activity");
		if(usService.loggedInUser != null) {
			Optional<ScheduledActivity> actOptional= Optional.of(repository.findByUser2(usService.loggedInUser.getEmail()));
			if (actOptional.isPresent()) {
				ScheduledActivity act= actOptional.get();
				 repository.delete(act); 
			}
			return repository.save(activity2);
		}
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}

	@Override
	public ScheduledActivity removeActivity() {
		if(usService.loggedInUser!= null) {
		LOG.info(usService.loggedInUser.toString());
		Optional<ScheduledActivity> actOptional= Optional.of(repository.findByUser2(usService.loggedInUser.getEmail()));
		if (actOptional.isPresent()) {
			ScheduledActivity act= actOptional.get();
			 repository.delete(act);
			 return act;
		}
		else {
			String exceptionMessage = "User with userName " + usService.loggedInUser.getEmail() + " not exists.";
			throw new UserNotFoundException(exceptionMessage);
		}
		}
		else {
		String exceptionMessage = "You are not logged in.";
		LOG.warn(exceptionMessage);
		throw new NotAuthorizedException(exceptionMessage);
		}
	}

	@Override
	public ScheduledActivity viewActivity() {
		if(usService.loggedInUser!= null) {
		LOG.info("Scheduled Activity");
		return (ScheduledActivity) repository.findByUser2(usService.loggedInUser.getEmail());
		}
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
			}
	}
	

}
