package com.capgemini.fitnesstracker.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.fitnesstracker.demo.model.WeeklyDiet;

@Repository
public interface WeeklyDietRepository extends JpaRepository<WeeklyDiet, Integer>{

	 
	
	public abstract WeeklyDiet findByWeeklyDietId(int weeklyDietId);
	
	@Query(value = "select sum(d.calorie) from DailyDiet d where d.user.email = :name1 and d.date between :date1 and :date2")
	public int viewPerDayDiet(@Param("name1") String userEmail, @Param ("date1") LocalDate date1, @Param ("date2") LocalDate date2);

	
	@Query(value = "select w.date, w.netCalorie, w.netPoint from WeeklyDiet w where w.user.email = :name1 and w.date = :date")
	public Object viewWeeklyDiet(@Param("name1") String userEmail, @Param("date") LocalDate date1);

	
	@Query(value = "select d.foodtype, sum(d.calorie) from DailyDiet d where d.user.email = :name1 and d.date between :date1 and :date2 group by d.foodtype")
	public Optional<List<Object>> viewWeeklyDietFoodTypeWise(@Param("name1") String userEmail, @Param("date1") LocalDate date1, @Param("date2") LocalDate date2);
	
	
}