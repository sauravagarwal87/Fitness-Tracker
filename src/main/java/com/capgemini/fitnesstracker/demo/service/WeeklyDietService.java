package com.capgemini.fitnesstracker.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.capgemini.fitnesstracker.demo.exception.ActivityNotFoundException;
import com.capgemini.fitnesstracker.demo.exception.InvalidDateDifferenceException;
import com.capgemini.fitnesstracker.demo.model.DailyDiet;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.model.WeeklyDiet;
import com.capgemini.fitnesstracker.demo.repository.WeeklyDietRepository;

/**
 * This is the service class of Weekly Diet Controller
 *
 */

@Service
public class WeeklyDietService implements IWeeklyDietService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WeeklyDiet weeklyDiet;
	
	@Autowired
	WeeklyDietRepository weeklyDietRepository;
	
	@Autowired
	User user;
	
	@Autowired
	UserService usService;
	
	@Autowired
	DailyDiet dailyDiet;
	
	@Override
	public WeeklyDiet addWeeklyDiet(WeeklyDiet diet) {
		LOG.info("Add Weekly Diet");
		return weeklyDietRepository.save(diet);
	}
	
	@Override
	public Object updateWeeklyDiet(LocalDate date1, LocalDate date2) throws InvalidDateDifferenceException {
		LOG.info("Update Weekly Diet");
		Period period = Period.between(date1, date2);
		if(period.getDays() == 7) {
			Optional<Object> diet = Optional.ofNullable(weeklyDietRepository.viewWeeklyDiet(usService.loggedInUser.getEmail(), date1));
			if(diet.isPresent()){
				return diet.get();
			}
			else {
				int calorie = weeklyDietRepository.viewPerDayDiet(usService.loggedInUser.getEmail(), date1, date2);
				
				int points = 1;
				if(calorie <500) {
					points = 5;
				}
				else if(calorie >500 && calorie <1000) {
					points = 4;
				}
				else if(calorie >1000 && calorie <2000) {
					points = 3;
				}
				else if(calorie >2000 && calorie<2500) {
					points = 2;
				}
				else {
					points = 1;
				}
				WeeklyDiet wd = new WeeklyDiet(usService.loggedInUser, date2, calorie, points);
				weeklyDietRepository.save(wd);
				return wd;
			}
		} 
		else {
			String exceptionMsg="Dates entered do not constitute a week";
			LOG.error(exceptionMsg);
			throw new InvalidDateDifferenceException(exceptionMsg);
		}
		
	}
	

	@Override
	public Object viewWeeklyDiet(LocalDate ld) {
		LOG.info("view weekly diet");
		return weeklyDietRepository.viewWeeklyDiet(usService.loggedInUser.getEmail(), ld);
	}

	@Override
	public List<Object> viewWeeklyDietFoodTypeWise(LocalDate date1, LocalDate date2) throws InvalidDateDifferenceException {
		LOG.info("view weekly diet Food Type wise");
		Period period = Period.between(date1, date2);
		if(period.getDays() == 7) {
			Optional<List<Object>> diet = weeklyDietRepository.viewWeeklyDietFoodTypeWise(usService.loggedInUser.getEmail(), date1, date2);
			if (!diet.get().isEmpty()) {
				return diet.get();
				
			}
			else {
				String exceptionMsg="Weekly Diet of this dates does not exists";
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
	public WeeklyDiet removeWeeklyDiet(int weeklyDietId) {
		LOG.info(Integer.toString(weeklyDietId));
		Optional<WeeklyDiet> weekOptional = Optional.of(weeklyDietRepository.findByWeeklyDietId(weeklyDietId));
		if(weekOptional.isPresent()) {
			WeeklyDiet diet = weekOptional.get();
			weeklyDietRepository.delete(diet);
			return diet;
					
		}
		else 
		{
			String exceptionMsg=" This Daily Diet do not exists";
			LOG.error(exceptionMsg);
			throw new ActivityNotFoundException(exceptionMsg);
		}
	}
	
}
