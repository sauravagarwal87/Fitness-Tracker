package com.capgemini.fitnesstracker.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.fitnesstracker.demo.model.ScheduledActivity;
import com.capgemini.fitnesstracker.demo.model.User;

public interface ScheduledActivityRepository extends JpaRepository<ScheduledActivity, Integer> {

	public abstract ScheduledActivity findByUser(String email);
	
	@Query(value = "select d from ScheduledActivity d where d.user.email = :email")
	public ScheduledActivity findByUser2(@Param ("email") String email);

}
