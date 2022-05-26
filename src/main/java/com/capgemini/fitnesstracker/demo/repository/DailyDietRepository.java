package com.capgemini.fitnesstracker.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.model.DailyDiet;
import com.capgemini.fitnesstracker.demo.model.User;

@Repository
public interface DailyDietRepository extends JpaRepository<DailyDiet , Integer> {

	public abstract Object findByDailyDietId(int dailyDietId);
	
	@Query(value = "select d.dailyDietId, d.date, d.foodtype, d.name, d.calorie from DailyDiet d where d.user.email = :email and d.date = :date")
	public List<Object> viewAllDietByDate(@Param ("email") String email, @Param ("date") LocalDate date);

	@Query(value = "select d from DailyDiet d where d.user.email = :email")
	public List<DailyDiet> viewAllDietByUser(@Param ("email") String email);
}