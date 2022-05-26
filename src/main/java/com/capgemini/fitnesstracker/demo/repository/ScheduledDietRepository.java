package com.capgemini.fitnesstracker.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.fitnesstracker.demo.model.ScheduledDiet;

@Repository
public interface ScheduledDietRepository extends JpaRepository<ScheduledDiet, Integer> {

	public abstract ScheduledDiet findByUser(String email);
	
	@Query(value="select d from ScheduledDiet d where d.user.email = :email")
	public ScheduledDiet findByUser2(@Param ("email") String email);
}