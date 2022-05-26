package com.capgemini.fitnesstracker.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.fitnesstracker.demo.exception.NotAuthorizedException;
import com.capgemini.fitnesstracker.demo.model.ScheduledActivity;
import com.capgemini.fitnesstracker.demo.model.ScheduledDiet;
import com.capgemini.fitnesstracker.demo.repository.ScheduledDietRepository;

/**
 * This is the service class of Scheduled Diet Controller
 *
 */
@Service
public class ScheduledDietService implements IScheduledDietService {
	
private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private ScheduledDietRepository repository;
	
	@Autowired
	UserService usService;
	
	@Override
	public ScheduledDiet updateDiet(ScheduledDiet diet) {
		LOG.info("Add Scheduled Diet");
		if(usService.loggedInUser != null) {
			Optional<ScheduledDiet> actOptional= Optional.ofNullable(repository.findByUser2(usService.loggedInUser.getEmail()));
			if (actOptional.isPresent()) {
				ScheduledDiet act= actOptional.get();
				 repository.delete(act); 
			}
			return repository.save(diet);
		}
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}
	

	@Override
	public ScheduledDiet removeDiet() {
		LOG.info(usService.loggedInUser.toString());
		Optional<ScheduledDiet> diettOptional= Optional.ofNullable(repository.findByUser(usService.loggedInUser.getEmail()));
		if (diettOptional.isPresent()) {
			ScheduledDiet diett= diettOptional.get();
			 repository.delete(diett);
			 return diett;
		}
		else {
		String exceptionMessage = "You are not logged in.";
		LOG.warn(exceptionMessage);
		throw new NotAuthorizedException(exceptionMessage);
		}
	}

	@Override
	public ScheduledDiet viewDiet() {
		LOG.info("Scheduled Diet");
		return (ScheduledDiet) repository.findByUser2(usService.loggedInUser.getEmail());
		
	}
	
}
