package com.capgemini.fitnesstracker.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.fitnesstracker.demo.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
	public abstract Admin findByUserName(String userName);
}
