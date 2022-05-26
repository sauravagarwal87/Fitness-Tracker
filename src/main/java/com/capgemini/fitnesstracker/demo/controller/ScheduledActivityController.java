package com.capgemini.fitnesstracker.demo.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.fitnesstracker.demo.model.ScheduledActivity;
import com.capgemini.fitnesstracker.demo.service.ScheduledActivityService;

/**
 * This controller class is used by user to add, update, view, remove scheduled daily activity records
 * 
 *
 */

@RestController
@RequestMapping("/scheduled-activity")
public class ScheduledActivityController implements IScheduledActivityController{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ScheduledActivityService service;



	@Override
	@GetMapping("/view-schedule-activity")
	public ResponseEntity<ScheduledActivity> viewScheduledActivity() {
		LOG.info("view-all-activities");
		ScheduledActivity actList = service.viewActivity();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Activities were found successfully.");
		ResponseEntity<ScheduledActivity> response = new ResponseEntity<>(actList, headers, HttpStatus.OK);
		return response;
	}



	@Override
	@PostMapping("/add-scheduled-activity")
	public ResponseEntity<ScheduledActivity> addScheduledActivities(@Valid @RequestBody ScheduledActivity activity) {
		LOG.info(activity.toString());
		ScheduledActivity act = service.updateActivity(activity);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Activity was created successfully.");
		ResponseEntity<ScheduledActivity> response = new ResponseEntity<>(act, headers, HttpStatus.CREATED);
		return response;
	}


	@Override
	@DeleteMapping("/remove-schedule-activity")
	public ResponseEntity<ScheduledActivity> removeScheduledActivities() {
		ScheduledActivity sca = service.removeActivity();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Removed Scheduled Diet");
		ResponseEntity<ScheduledActivity> response = new ResponseEntity<>(sca, headers, HttpStatus.OK);
		return response;
	}
}
