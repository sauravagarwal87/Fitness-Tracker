package com.capgemini.fitnesstracker.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capgemini.fitnesstracker.demo.model.DailyDiet;

public interface IDailyDietController {

	public abstract ResponseEntity<DailyDiet> addDailyDiets(DailyDiet diet);

	public abstract ResponseEntity<DailyDiet> updateDailyDiets(DailyDiet diet);

	public abstract ResponseEntity<DailyDiet> removeDailyDiets(int dailyDietId);

	public abstract ResponseEntity<List<Object>> viewDailyDiets(String s);

	ResponseEntity<List<DailyDiet>> viewAllDiets();

}
