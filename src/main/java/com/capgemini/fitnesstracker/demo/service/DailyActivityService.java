package com.capgemini.fitnesstracker.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.fitnesstracker.demo.exception.ActivityNotFoundException;
import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.repository.DailyActivityRepsitory;

import com.capgemini.fitnesstracker.demo.model.User;

/**
 * This is the service class of Daily Activity Controller
 *
 */


@Service
public class DailyActivityService implements IDailyActivityService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	
	
//	@Autowired
//	DailyActivity dailyActivity;
	
	@Autowired
	DailyActivityRepsitory dailyActivityRepsitory;
	
	
	@Autowired
	User user;
	
	@Autowired
	UserService usService;
	
	@Override
	public DailyActivity addActivity(DailyActivity activity) {
		LOG.info(activity.toString());
		return dailyActivityRepsitory.save(activity);
	}

	@Override
	public DailyActivity updateActivity(DailyActivity activity) {
		LOG.info("Update Daily Activity");
		Optional<DailyActivity> actOptional = dailyActivityRepsitory.findById(activity.getDailyActivityId());
		if (actOptional.isPresent())
			return dailyActivityRepsitory.save(activity);
		else {
			String exceptionMessage = "Daily Activity of "+activity.getDailyActivityId()+" do not exists" ;
			LOG.warn(exceptionMessage);
			throw new ActivityNotFoundException(exceptionMessage);
		}
		
	}

	@Override
	public DailyActivity removeActivity(Integer aid) {
		LOG.info(Integer.toString(aid));
		Optional<DailyActivity> actOptional= dailyActivityRepsitory.findById(aid);
		if (actOptional.isPresent()) {
			DailyActivity act= actOptional.get();
			 dailyActivityRepsitory.delete(act);
			 return act;
		}
		else
			throw new ActivityNotFoundException("Daily Activity of "+aid+" do not exists" );
 		
		
	}
	
	

	
	public List<DailyActivity> viewActivity() {
		LOG.info("All Activities");
		return dailyActivityRepsitory.viewAllActivityByUser(usService.loggedInUser.getEmail()); 
	}



	@Override
	public List<Object> viewDailyActivity(LocalDate date){

		LOG.info("Daily Activity");

		//UserDailyId usdi = new UserDailyId(usService.loggedInUser, date, category );
		return (List<Object>) dailyActivityRepsitory.viewAllActivityByDate(usService.loggedInUser.getEmail(),date);

		
		//return  dailyActivityRepsitory.viewAllActivityByDate(usService.loggedInUser.getEmail(),date);

	}
	
	
	public List<Object> viewActivityGroupByDate(){
		LOG.info("Daily Activity");
		return (List<Object>) dailyActivityRepsitory.viewActGrpByDate(usService.loggedInUser.getEmail());


	}

}
