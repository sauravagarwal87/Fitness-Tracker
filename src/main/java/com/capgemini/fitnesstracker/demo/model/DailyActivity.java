package com.capgemini.fitnesstracker.demo.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.fitnesstracker.demo.model.DailyDiet.foodType;

@Entity
@Table(name = "daily_activity")
@Component
public class DailyActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Daily_Activity_Id")
	private int dailyActivityId;
	
	@ManyToOne
	@JoinColumn(name = "email_id")
	private User user;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "category")
	@Enumerated(EnumType.ORDINAL)
	private Category category;

	@Column(name = "frequency")
	@Min(value=0, message = "Frequncy can't be negative")
	@Digits(integer=4,fraction=0)
	private int frequency;

	@Column(name = "duration")
	@Min(value=0, message = "Duration can't be negative")
	@Digits(integer=4,fraction=2)
	private int duration;
	
	@Column(name = "calorie")
	@Min(value=0, message = "Calorie can't be negative")
	@Digits(integer=6,fraction=2)
	private int calorie;

	public DailyActivity() {
		super();
		
	}


	public DailyActivity(int dailyActivityId, User user, LocalDate date, Category category, int frequency, int duration,
			int calorie) {
		super();
		this.dailyActivityId = dailyActivityId;
		this.user = user;
		this.date = date;
		this.category = category;
		this.frequency = frequency;
		this.duration = duration;
		this.calorie = calorie;
	}







	public int getDailyActivityId() {
		return dailyActivityId;
	}

	public void setDailyActivityId(int dailyActivityId) {
		this.dailyActivityId = dailyActivityId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	
	

	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}

	

	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}







	@Override
	public int hashCode() {
		return Objects.hash(calorie, category, dailyActivityId, date, duration, frequency, user);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyActivity other = (DailyActivity) obj;
		return calorie == other.calorie && category == other.category && dailyActivityId == other.dailyActivityId
				&& Objects.equals(date, other.date) && duration == other.duration && frequency == other.frequency
				&& Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "DailyActivity [dailyActivityId=" + dailyActivityId + ", user=" + user + ", date=" + date + ", category="
				+ category + ", frequency=" + frequency + ", duration=" + duration + ", calorie=" + calorie + "]";
	}

	
	
	
}
