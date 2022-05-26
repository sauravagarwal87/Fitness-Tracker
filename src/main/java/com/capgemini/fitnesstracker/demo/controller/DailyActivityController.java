package com.capgemini.fitnesstracker.demo.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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

import com.capgemini.fitnesstracker.demo.model.Category;
import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.model.User;
import com.capgemini.fitnesstracker.demo.service.DailyActivityService;


/**
 * This controller class is used by user to add, update, view, remove daily activity records
 * 
 *
 */



@RestController
@RequestMapping("/daily-activity")
@CrossOrigin(origins="http://localhost:3000")
public class DailyActivityController implements IDailyActivityController{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());


	@Autowired
	DailyActivityService service;


	@Override
	@GetMapping("/view-all-activity")
	public ResponseEntity<List<DailyActivity>> viewAllActivities() {
		LOG.info("view-all-activities");
		List<DailyActivity> actList = service.viewActivity();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Activities were found successfully.");
		ResponseEntity<List<DailyActivity>> response = new ResponseEntity<>(actList, headers, HttpStatus.OK);
		return response;
	}

	@Override
	@PostMapping("/add-activity")
	public ResponseEntity<DailyActivity> addDailyActivities(@Valid @RequestBody DailyActivity activity) {
		LOG.info(activity.toString());
		DailyActivity act = service.addActivity(activity);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Activity was created successfully.");
		ResponseEntity<DailyActivity> response = new ResponseEntity<>(act, headers, HttpStatus.CREATED);
		return response;
	}

	@Override
	@PutMapping("/update-activity")
	public ResponseEntity<DailyActivity> updateDailyActivities(@Valid @RequestBody DailyActivity activity) {
		LOG.info(activity.toString());
		DailyActivity act= service.updateActivity(activity);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Activity was updated successfully.");
		ResponseEntity<DailyActivity> response = new ResponseEntity<>(act, headers, HttpStatus.OK);
		return response;
	}

	@Override
	@DeleteMapping("/remove-activity")
	public ResponseEntity<DailyActivity> removeDailyActivities(@RequestParam (name="aid") int actId) {
		LOG.info(Integer.toString(actId));
		DailyActivity da = service.removeActivity(actId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Activity  was deleted successfully.");
		ResponseEntity<DailyActivity> response = new ResponseEntity<>(da, headers, HttpStatus.OK);
		return response;
	}


	@Override
	@GetMapping("/view-daily-activity")
	public ResponseEntity<List<Object>> viewDailyActivity(@RequestParam (name = "Date") String d){

		LOG.info("View Daily Activities");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(d, formatter);
		List<Object> act = service.viewDailyActivity(localDate);

		HttpHeaders headers = new HttpHeaders();

		headers.add("message", "Daily Activities were found successfully.");
		ResponseEntity<List<Object>> response = new ResponseEntity<>(act, headers, HttpStatus.OK);

		return response;

	}
	
	
	
	@GetMapping("/view-activity-grp-by-date")
	public ResponseEntity<List<Object>> viewActivitiesGroupByDate() {
		LOG.info("view-activity-grp-by-date");
		List<Object> actList = service.viewActivityGroupByDate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Activities were found successfully.");
		ResponseEntity<List<Object>> response = new ResponseEntity<>(actList, headers, HttpStatus.OK);
		return response;
	}


}
