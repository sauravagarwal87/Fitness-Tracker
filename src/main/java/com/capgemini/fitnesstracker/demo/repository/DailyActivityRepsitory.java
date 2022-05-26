package com.capgemini.fitnesstracker.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.capgemini.fitnesstracker.demo.model.Category;
import com.capgemini.fitnesstracker.demo.model.DailyActivity;
import com.capgemini.fitnesstracker.demo.model.User;



@Repository
public interface DailyActivityRepsitory extends JpaRepository<DailyActivity , Integer> {
	@Query(value = "select d from DailyActivity d where d.user.email = :email and d.date = :date")
	public List<Object> viewAllActivityByDate(@Param ("email") String email, @Param ("date") LocalDate date);
	
	@Query(value = "select d from DailyActivity d where d.user.email = :email order by date")
	public List<DailyActivity> viewAllActivityByUser(@Param ("email") String email);
	
	@Query(value= "select sum(d.calorie),date from DailyActivity d where  d.user.email = :email group by date")
	public List<Object> viewActGrpByDate(@Param ("email") String email);
}