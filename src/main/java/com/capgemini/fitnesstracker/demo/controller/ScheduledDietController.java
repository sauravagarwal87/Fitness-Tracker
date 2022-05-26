package com.capgemini.fitnesstracker.demo.controller;

import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.fitnesstracker.demo.model.ScheduledDiet;
import com.capgemini.fitnesstracker.demo.service.ScheduledDietService;

/**
 * This controller class is used by user to add, update, view, remove scheduled daily diet records
 * 
 *
 */


@RestController
@RequestMapping("/sd")
@CrossOrigin(origins="http://localhost:3000")
public class ScheduledDietController implements ISheduledDietController{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());


	@Autowired
	ScheduledDietService service;

	@Override
	@PutMapping("/update-diet")
	public ResponseEntity<ScheduledDiet> updateDiet(@Valid @RequestBody ScheduledDiet diet) {
		LOG.info(diet.toString());
		ScheduledDiet diet1 = service.updateDiet(diet);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Food was updated successfully.");
		ResponseEntity<ScheduledDiet> response = new ResponseEntity<>(diet1, headers, HttpStatus.OK);
		return response;
	}


	@Override
	@GetMapping("/view-diet")
	public ResponseEntity<ScheduledDiet> viewDiet() {
//		LOG.info(date.toString());
		ScheduledDiet diettList = service.viewDiet();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "ScheduledDiet were found successfully.");
		ResponseEntity<ScheduledDiet> response = new ResponseEntity<>(diettList, headers, HttpStatus.OK);
		return response;
	}



	@Override
	@DeleteMapping("/remove-diet")
	public ResponseEntity<ScheduledDiet> removeDiet() {
		ScheduledDiet sca = service.removeDiet();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employees are.");
		ResponseEntity<ScheduledDiet> response = new ResponseEntity<>(sca, headers, HttpStatus.OK);
		return response;
	}
}







