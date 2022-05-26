package com.capgemini.fitnesstracker.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.fitnesstracker.demo.model.WeeklyActivity;


@Repository
public interface WeeklyActivityRepository extends JpaRepository<WeeklyActivity, Integer>{

	 
	
	public abstract WeeklyActivity findByWeeklyActivityId(int weeklyActivityId);
	
	@Query(value = "select sum(d.calorie) from DailyActivity d where d.user.email = :name1 and d.date between :date1 and :date2 ")
	public int viewPerDayActivity(@Param("name1") String userEmail, @Param("date1") LocalDate date1, @Param("date2") LocalDate date2);
	
	
	@Query(value = "select w.date, w.netCalorie, w.netPoint from WeeklyActivity w where w.user.email = :name1 and w.date = :date1")
	public Object viewWeeklyActivity(@Param("name1") String userEmail, @Param("date1") LocalDate date1);
	
	
	
	@Query(value = "select d.category, sum(d.calorie), sum(d.frequency), sum(d.duration) from DailyActivity d where d.user.email = :name1 and d.date between :date1 and :date2 group by d.category")
	public List<Object> viewWeeklyActivityCatwise(@Param("name1") String userEmail, @Param("date1") LocalDate date1, @Param("date2") LocalDate date2);
	
}
