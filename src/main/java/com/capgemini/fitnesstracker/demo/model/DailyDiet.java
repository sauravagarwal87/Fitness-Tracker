package com.capgemini.fitnesstracker.demo.model;

import java.time.LocalDate;

import java.util.Objects;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="daily_diet")
@Component
public class DailyDiet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DailyDiet_id")
	private int dailyDietId;
	
	@ManyToOne
	@JoinColumn(name = "email_id")
	private User user;
	
	@Column(name = "Date")
	private LocalDate date;
	
	@Column(name = "food_type")
	@Enumerated(EnumType.ORDINAL)
	private foodType foodtype;
	
	@Column(name="food_name")
	private String name;
	
	@Column(name = "calorie")
	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
    private Integer calorie;

	public enum foodType {
		
		BREAKFAST, BRUNCH, LUNCH, EVENINGSNACK, DINNER
	
	}

	public DailyDiet() {
		super();
	}

	public DailyDiet(int dailyDietId, User user, LocalDate date, foodType foodtype, String name, Integer calorie) {
		super();
		this.dailyDietId = dailyDietId;
		this.user = user;
		this.date = date;
		this.foodtype = foodtype;
		this.name = name;
		this.calorie = calorie;
	}

	public int getDailyDietId() {
		return dailyDietId;
	}

	public void setDailyDietId(int dailyDietId) {
		this.dailyDietId = dailyDietId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public foodType getFoodtype() {
		return foodtype;
	}

	public void setFoodtype(foodType foodtype) {
		this.foodtype = foodtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCalorie() {
		return calorie;
	}

	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
	}

	@Override
	public int hashCode() {
		return Objects.hash(calorie, dailyDietId, date, foodtype, name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyDiet other = (DailyDiet) obj;
		return Objects.equals(calorie, other.calorie) && dailyDietId == other.dailyDietId
				&& Objects.equals(date, other.date) && foodtype == other.foodtype && Objects.equals(name, other.name)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "DailyDietNew [dailyDietId=" + dailyDietId + ", user=" + user + ", date=" + date + ", foodtype="
				+ foodtype + ", name=" + name + ", calorie=" + calorie + "]";
	}

	
}
