package com.capgemini.fitnesstracker.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.capgemini.fitnesstracker.demo.exception.ActivityNotFoundException;
import com.capgemini.fitnesstracker.demo.exception.InvalidDateDifferenceException;
import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.model.WeeklyActivity;
import com.capgemini.fitnesstracker.demo.model.WeeklyDiet;
import com.capgemini.fitnesstracker.demo.repository.WeeklyActivityRepository;

/**
 * This is the service class of Weekly Activity Controller
 *
 */

@Service
public class WeeklyActivityService implements IWeeklyActivityService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WeeklyActivity weeklyActivity;



	@Autowired
	WeeklyActivityRepository weeklyActivityRepository;

	@Autowired
	User user;

	@Autowired
	UserService usService;

	@Autowired
	DailyActivity dailyActivity;

	@Override
	public WeeklyActivity addWeeklyActivity(WeeklyActivity activity) {
		LOG.info("Add Weekly Activity");
		return weeklyActivityRepository.save(activity);
	}
	

	@Override
	public Object updateWeeklyActivity(LocalDate date1, LocalDate date2) throws InvalidDateDifferenceException{
		LOG.info("Update Weekly Activity");
		Period period = Period.between(date1, date2);
		if(period.getDays() == 7) {
			Optional<Object> activity = Optional.ofNullable(weeklyActivityRepository.viewWeeklyActivity(usService.loggedInUser.getEmail(), date1));
			if (activity.isPresent()) {
				return activity.get();
				
			} else {
				int cal= weeklyActivityRepository.viewPerDayActivity(usService.loggedInUser.getEmail(), date1, date2);
				
				
				int points = 1;
				if (cal < 500) {
					points = 1;
				} else if (cal > 500 && cal < 1000) {
					points = 2;
				} else if (cal > 1000 && cal < 2000) {
					points = 3;
				} else if (cal > 2000 && cal < 2500) {
					points = 4;
				} else {
					points = 5;
				}
				WeeklyActivity weekact = new WeeklyActivity(usService.loggedInUser, date2, cal, points);
				weeklyActivityRepository.save(weekact);
				return weekact;
			}
		}
		else {
			String exceptionMsg="Dates entered do not constitute a week";
			LOG.error(exceptionMsg);
			throw new InvalidDateDifferenceException(exceptionMsg);
		}

	}

	
	
	@Override
	public Object viewWeeklyActivity(LocalDate date) {
		LOG.info("view weekly activity");
		return weeklyActivityRepository.viewWeeklyActivity(usService.loggedInUser.getEmail(), date);
	}
	
	public List<Object> viewWeeklyActivityCatWise(LocalDate date1, LocalDate date2) throws InvalidDateDifferenceException {
		LOG.info("view weekly activity Cat wise"+date1);
		Period period = Period.between(date1, date2);
		if(period.getDays() == 7) {
			List<Object> activity = weeklyActivityRepository.viewWeeklyActivityCatwise(usService.loggedInUser.getEmail(), date1, date2);
			if (!activity.isEmpty()) {
				return activity;
				
			}
			else {
				String exceptionMsg="Weekly Activity of this dates does not exists";
				LOG.error(exceptionMsg);
				throw new ActivityNotFoundException(exceptionMsg);
			}
		}
		else {
			String exceptionMsg="Dates entered do not constitute a week";
			LOG.error(exceptionMsg);
			throw new InvalidDateDifferenceException(exceptionMsg);
		}
	}


	@Override
	public WeeklyActivity removeWeeklyActivity(int weeklyActivityId) {
		LOG.info(Integer.toString(weeklyActivityId));
		Optional<WeeklyActivity> weekOptional = Optional
				.of(weeklyActivityRepository.findByWeeklyActivityId(weeklyActivityId));
		if (weekOptional.isPresent()) {
			WeeklyActivity act = weekOptional.get();
			weeklyActivityRepository.delete(act);
			return act;

		} else
		{
			String exceptionMsg="Weekly Activity of "+weeklyActivityId+" do not exists";
			LOG.error(exceptionMsg);
			throw new ActivityNotFoundException(exceptionMsg);
		}
	}
	
	



}
