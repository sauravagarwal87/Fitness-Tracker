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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.model.DailyDiet;
import com.capgemini.fitnesstracker.demo.service.DailyDietService;


/**
 * This controller class is used by user to add, update, view, remove daily diet records
 * 
 *
 */

@RestController
@RequestMapping("/daily-diet")
@CrossOrigin(origins="http://localhost:3000")
public class DailyDietController implements IDailyDietController{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());


	@Autowired
	DailyDietService service;

	@Override
	@PostMapping("/add-diet")
	public ResponseEntity<DailyDiet> addDailyDiets(@Valid @RequestBody DailyDiet diet) {
		LOG.info(diet.toString());
		DailyDiet diet1 = service.addDiet(diet);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Diet was added successfully.");
		ResponseEntity<DailyDiet> response = new ResponseEntity<>(diet1, headers, HttpStatus.CREATED);
		return response;
	}

	@Override
	@PutMapping("/update-diet")
	public ResponseEntity<DailyDiet> updateDailyDiets(@Valid @RequestBody DailyDiet diet) {
		LOG.info(diet.toString());
		DailyDiet diet1 = service.updateDiet(diet);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Food was updated successfully.");
		ResponseEntity<DailyDiet> response = new ResponseEntity<>(diet1, headers, HttpStatus.OK);
		return response;
	}

	@Override
	@DeleteMapping("/remove-diet/{did}")
	public ResponseEntity<DailyDiet> removeDailyDiets(@PathVariable(name = "did") int dailyDietId) {
		LOG.info(Integer.toString(dailyDietId));
		DailyDiet diet1 = service.removeDiet(dailyDietId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Diet was deleted successfully.");
		ResponseEntity<	DailyDiet> response = new ResponseEntity<>(diet1, headers, HttpStatus.OK);
		return response;
	}
	
	@Override
	@GetMapping("/view-all-diet")
	public ResponseEntity<List<DailyDiet>> viewAllDiets() {
		LOG.info("view-all-diets");
		List<DailyDiet> dietList = service.viewDiet();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Diets were found successfully.");
		ResponseEntity<List<DailyDiet>> response = new ResponseEntity<>(dietList, headers, HttpStatus.OK);
		return response;
	}

	@Override
	@GetMapping("/view-daily-diet")
	public ResponseEntity<List<Object>> viewDailyDiets(@Valid @RequestParam (name = "Date") String d) {
		LOG.info("View Daily Diets");
		//LocalDate ld = LocalDate.of(2022,04,06);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(d, formatter);
		List<Object> diet1 = service.viewDailyDiet(localDate);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Food was updated successfully.");
		ResponseEntity<List<Object>> response = new ResponseEntity<>(diet1, headers, HttpStatus.OK);
		return response;
	}	

}