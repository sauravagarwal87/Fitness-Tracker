package com.capgemini.fitnesstracker.demo.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "weekly_diet")
@Component
public class WeeklyDiet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weeklyDietId")
	private int weeklyDietId;
	
	@ManyToOne
	@JoinColumn(name = "email_id")
	private User user;
	
	private LocalDate date;
	
	private int netCalorie;
	
	private int netPoint;

	public WeeklyDiet() {
		super();
		
	}
	
	

	public WeeklyDiet(User user, LocalDate date, int netCalorie, int netPoint) {
		super();
		this.user = user;
		this.date = date;
		this.netCalorie = netCalorie;
		this.netPoint = netPoint;
	}



	public int getWeeklyDietId() {
		return weeklyDietId;
	}

	public void setWeeklyDietId(int weeklyDietId) {
		this.weeklyDietId = weeklyDietId;
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

	public int getNetCalorie() {
		return netCalorie;
	}

	public void setNetCalorie(int netCalorie) {
		this.netCalorie = netCalorie;
	}

	public int getNetPoint() {
		return netPoint;
	}

	public void setNetPoint(int netPoint) {
		this.netPoint = netPoint;
	}



	@Override
	public int hashCode() {
		return Objects.hash(date, netCalorie, netPoint, user, weeklyDietId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeeklyDiet other = (WeeklyDiet) obj;
		return Objects.equals(date, other.date) && netCalorie == other.netCalorie && netPoint == other.netPoint
				&& Objects.equals(user, other.user) && weeklyDietId == other.weeklyDietId;
	}



	@Override
	public String toString() {
		return "WeeklyDiet [weeklyDietId=" + weeklyDietId + ", user=" + user + ", date=" + date + ", netCalorie="
				+ netCalorie + ", netPoint=" + netPoint + "]";
	}
	
	
	
}
