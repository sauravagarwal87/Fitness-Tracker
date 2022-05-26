package com.capgemini.fitnesstracker.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.capgemini.fitnesstracker.demo.exception.DietNotFoundException;
import com.capgemini.fitnesstracker.demo.exception.UserNotFoundException;
import com.capgemini.fitnesstracker.demo.exception.ActivityNotFoundException;
import com.capgemini.fitnesstracker.demo.exception.DietNotFoundException;
import com.capgemini.fitnesstracker.demo.model.DailyDiet;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.repository.DailyDietRepository;

/**
 * This is the service class of Daily Diet Controller
 *
 */

@Service
@Component
public class DailyDietService implements IDailyDietService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DailyDietRepository dailyDietRepository;

//	@Autowired
//	DailyDiet dailyDiet;
	
	@Autowired
	User user;

	@Autowired
	UserService usService;

	@Override
	public DailyDiet addDiet(DailyDiet diet) {
		LOG.info("Daily Diet" + diet.toString() + " added.");
		return dailyDietRepository.save(diet);
	}

	@Override
	public DailyDiet updateDiet(DailyDiet diet) {
		LOG.info("Diet " + diet.toString() + "updated.");
		Optional<DailyDiet> dietOptional = dailyDietRepository.findById(diet.getDailyDietId());
		if (dietOptional.isPresent())
			return dailyDietRepository.save(diet);
		else {
			String exceptionMessage = "Diet " + diet + "not found.";
			LOG.warn(exceptionMessage);
			throw new DietNotFoundException(exceptionMessage);
		}

	}

	@Override
	public DailyDiet removeDiet(Integer did) {
		LOG.info(Integer.toString(did));
		Optional<DailyDiet> dietOptional = dailyDietRepository.findById(did);
		if (dietOptional.isPresent()) {
			DailyDiet diet1 = dietOptional.get();
			dailyDietRepository.delete(diet1);
			return diet1;
		}
		else
			throw new DietNotFoundException("Daily diet of" + did + "not found");

	}

	@Override
	public List<DailyDiet> viewDiet() {
		LOG.info("All Diets");

		return dailyDietRepository.viewAllDietByUser(usService.loggedInUser.getEmail());

	}
	
	@Override
	public List<Object> viewDailyDiet(LocalDate date) {
		LOG.info("Daily Diet");
		return (List<Object>) dailyDietRepository.viewAllDietByDate(usService.loggedInUser.getEmail(), date);
	}
}