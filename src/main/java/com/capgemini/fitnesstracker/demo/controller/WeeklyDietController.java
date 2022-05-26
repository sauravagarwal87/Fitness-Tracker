package com.capgemini.fitnesstracker.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.model.WeeklyActivity;
import com.capgemini.fitnesstracker.demo.model.WeeklyDiet;
import com.capgemini.fitnesstracker.demo.service.WeeklyDietService;

/**
 * This controller class is used by user to  view, update, remove weekly diet records
 * 
 *
 */

@RestController
@RequestMapping("/weekly-diet")
public class WeeklyDietController implements IWeeklyDietController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WeeklyDietService weeklyDietService;
	
	@Autowired
	User user;

	@Override
	@PostMapping("/update-weekly-diet")
	public ResponseEntity<Object> updateWeeklyDiets(@Valid @RequestParam String d1, @RequestParam String d2) {
		LOG.info("Update weekly diet");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.parse(d1, formatter);
		LocalDate date2 = LocalDate.parse(d2, formatter);
		Object diet = weeklyDietService.updateWeeklyDiet(date1, date2);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Weekly Diet was updated successfully.");
		ResponseEntity<Object> response = new ResponseEntity<>(diet, headers, HttpStatus.OK);
		return response;
	}


	@Override
	@DeleteMapping("/remove-weekly-diet/{did}")
	public ResponseEntity<WeeklyDiet> removeWeeklyDiets(@PathVariable(name = "did") int weeklyDietId) {
		LOG.info(Integer.toString(weeklyDietId));
		WeeklyDiet weekdiet = weeklyDietService.removeWeeklyDiet(weeklyDietId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Weekly Diets  was deleted successfully.");
		ResponseEntity<WeeklyDiet> response = new ResponseEntity<>(weekdiet, headers, HttpStatus.OK);
		return response;
	}


	@Override
	@GetMapping("/view-weekly-diet")
	public ResponseEntity<Object> viewWeeklyDiets(@RequestParam (name = "Date") String d){
		LOG.info("View Weekly Diet");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.parse(d, formatter);
		Object weekDiet = weeklyDietService.viewWeeklyDiet(date1);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Weekly Diets were found successfully.");
		ResponseEntity<Object> response = new ResponseEntity<>(weekDiet, headers, HttpStatus.OK);
		return response;

	}
	
	@GetMapping("/view-weekly-diet-foodType")
	public ResponseEntity<List<Object>> viewWeeklyDietFoodTypeWise(@RequestParam (name = "Date") String d1, @RequestParam String d2){
		LOG.info("View Weekly Diets");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.parse(d1, formatter);
		LocalDate date2 = LocalDate.parse(d2, formatter);
		List<Object> weekDiet = weeklyDietService.viewWeeklyDietFoodTypeWise(date1, date2);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Weekly Diets were found successfully.");
		ResponseEntity<List<Object>> response = new ResponseEntity<>(weekDiet, headers, HttpStatus.OK);
		return response;
	}


}
