package com.capgemini.fitnesstracker.demo.controller;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import com.capgemini.fitnesstracker.demo.model.ScheduledDiet;

public interface ISheduledDietController {




	public abstract ResponseEntity<ScheduledDiet> updateDiet(ScheduledDiet Diet);

	public abstract ResponseEntity<ScheduledDiet> viewDiet();

	public abstract ResponseEntity<ScheduledDiet> removeDiet();
}