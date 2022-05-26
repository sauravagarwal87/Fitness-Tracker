package com.capgemini.fitnesstracker.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capgemini.fitnesstracker.demo.model.WeeklyDiet;

public interface IWeeklyDietController {

	public abstract ResponseEntity<Object> updateWeeklyDiets(String d1, String d2);

	public abstract ResponseEntity<WeeklyDiet> removeWeeklyDiets(int weeklyDietId);

	public abstract ResponseEntity<Object> viewWeeklyDiets(String d);



}
