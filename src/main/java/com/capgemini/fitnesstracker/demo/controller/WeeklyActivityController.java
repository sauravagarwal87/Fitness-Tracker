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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.model.WeeklyActivity;

import com.capgemini.fitnesstracker.demo.service.DailyActivityService;
import com.capgemini.fitnesstracker.demo.service.WeeklyActivityService;

/**
 * This controller class is used by user to  update, view, remove weekly activity records
 * 
 *
 */


@RestController
@RequestMapping("/weekly-activity")
@CrossOrigin(origins="http://localhost:3000")
public class WeeklyActivityController implements IWeeklyActivityController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());


	@Autowired
	WeeklyActivityService service;


	@Autowired
	User user;

	@Override
	@PostMapping("/update-weekly-activity")
	public ResponseEntity<Object> updateWeeklyActivities(@RequestParam String d1,@RequestParam String d2) {
		LOG.info("Update weekly activity");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.parse(d1, formatter);
		LocalDate date2 = LocalDate.parse(d2, formatter);
		Object act= service.updateWeeklyActivity(date1, date2);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Weekly Activity was updated successfully.");
		ResponseEntity<Object> response = new ResponseEntity<>(act, headers, HttpStatus.OK);
		return response;
	}

	@Override
	@DeleteMapping("/remove-weekly-activity/{aid}")
	public ResponseEntity<WeeklyActivity> removeWeeklyActivities(@PathVariable(name = "aid") int weeklyId) {
		LOG.info(Integer.toString(weeklyId));
		WeeklyActivity weekAct = service.removeWeeklyActivity(weeklyId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Weekly Activity  was deleted successfully.");
		ResponseEntity<WeeklyActivity> response = new ResponseEntity<>(weekAct, headers, HttpStatus.OK);
		return response;
	}

	@Override
	@GetMapping("/view-weekly-activity")
	public ResponseEntity<Object> viewWeeklyActivity(@RequestParam (name = "Date") String d){
		LOG.info("View Weekly Activities");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate date1 = LocalDate.parse(d, formatter);
		Object weekAct = service.viewWeeklyActivity(date1);

		
		HttpHeaders headers = new HttpHeaders();
     	headers.add("message", "Weekly Activities were found successfully.");
		ResponseEntity<Object> response = new ResponseEntity<>(weekAct, headers, HttpStatus.OK);
		return response;

	}
	
	
	
	@GetMapping("/view-weekly-activity-cat")
	public ResponseEntity<List<Object>> viewWeeklyActivityCatWise(@RequestParam (name = "Date") String d1, @RequestParam String d2){
		LOG.info("View Weekly Activities");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.parse(d1, formatter);
		LocalDate date2 = LocalDate.parse(d2, formatter);
		List<Object> weekAct = service.viewWeeklyActivityCatWise(date1, date2);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Weekly Activities were found successfully.");
		ResponseEntity<List<Object>> response = new ResponseEntity<>(weekAct, headers, HttpStatus.OK);
		return response;
	}




}

