package com.capgemini.fitnesstracker.demo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

@Entity

@Table(name = "schedule_activity")

@Component
public class ScheduledActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int activityId;

	@ManyToOne
	@JoinColumn(name = "email_id")
	private User user;

	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
	private double day1Calorie;
	
	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
	private double day2Calorie;
	
	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
	private double day3Calorie;
	
	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
	private double day4Calorie;
	
	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
	private double day5Calorie;
	
	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
	private double day6Calorie;
	
	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
	private double day7Calorie;

	public ScheduledActivity() {
		super();
	}

	public ScheduledActivity(User user, double day1Calorie, double day2Calorie, double day3Calorie, double day4Calorie,
			double day5Calorie, double day6Calorie, double day7Calorie) {
		super();
		this.user = user;
		this.day1Calorie = day1Calorie;
		this.day2Calorie = day2Calorie;
		this.day3Calorie = day3Calorie;
		this.day4Calorie = day4Calorie;
		this.day5Calorie = day5Calorie;
		this.day6Calorie = day6Calorie;
		this.day7Calorie = day7Calorie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getDay1Calorie() {
		return day1Calorie;
	}

	public void setDay1Calorie(double day1Calorie) {
		this.day1Calorie = day1Calorie;
	}

	public double getDay2Calorie() {
		return day2Calorie;
	}

	public void setDay2Calorie(double day2Calorie) {
		this.day2Calorie = day2Calorie;
	}

	public double getDay3Calorie() {
		return day3Calorie;
	}

	public void setDay3Calorie(double day3Calorie) {
		this.day3Calorie = day3Calorie;
	}

	public double getDay4Calorie() {
		return day4Calorie;
	}

	public void setDay4Calorie(double day4Calorie) {
		this.day4Calorie = day4Calorie;
	}

	public double getDay5Calorie() {
		return day5Calorie;
	}

	public void setDay5Calorie(double day5Calorie) {
		this.day5Calorie = day5Calorie;
	}

	public double getDay6Calorie() {
		return day6Calorie;
	}

	public void setDay6Calorie(double day6Calorie) {
		this.day6Calorie = day6Calorie;
	}

	public double getDay7Calorie() {
		return day7Calorie;
	}

	public void setDay7Calorie(double day7Calorie) {
		this.day7Calorie = day7Calorie;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day1Calorie, day2Calorie, day3Calorie, day4Calorie, day5Calorie, day6Calorie, day7Calorie,
				user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduledActivity other = (ScheduledActivity) obj;
		return Double.doubleToLongBits(day1Calorie) == Double.doubleToLongBits(other.day1Calorie)
				&& Double.doubleToLongBits(day2Calorie) == Double.doubleToLongBits(other.day2Calorie)
				&& Double.doubleToLongBits(day3Calorie) == Double.doubleToLongBits(other.day3Calorie)
				&& Double.doubleToLongBits(day4Calorie) == Double.doubleToLongBits(other.day4Calorie)
				&& Double.doubleToLongBits(day5Calorie) == Double.doubleToLongBits(other.day5Calorie)
				&& Double.doubleToLongBits(day6Calorie) == Double.doubleToLongBits(other.day6Calorie)
				&& Double.doubleToLongBits(day7Calorie) == Double.doubleToLongBits(other.day7Calorie)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "ScheduledActivity [user=" + user + ", day1Calorie=" + day1Calorie + ", day2Calorie=" + day2Calorie
				+ ", day3Calorie=" + day3Calorie + ", day4Calorie=" + day4Calorie + ", day5Calorie=" + day5Calorie
				+ ", day6Calorie=" + day6Calorie + ", day7Calorie=" + day7Calorie + "]";
	}

}
