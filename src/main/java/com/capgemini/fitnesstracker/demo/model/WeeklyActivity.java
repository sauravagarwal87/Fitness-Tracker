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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

@Entity
@Table(name= "weekly_activity")
@Component
public class WeeklyActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weeklyActivityId")
	private int weeklyActivityId;
	
	@ManyToOne
	@JoinColumn(name = "email_id")
	private User user;
	
	private LocalDate date;
	
	@Column(name="net_calorie")
	private int netCalorie;
	
	@Column(name="net_point")
	private int netPoint;

	public WeeklyActivity() {
		super();
		
	}

	
	
	public WeeklyActivity( User user, LocalDate date, int netCalorie, int netPoint) {
		super();
	
		this.user = user;
		this.date = date;
		this.netCalorie = netCalorie;
		this.netPoint = netPoint;
	}



	public int getWeeklyActivityId() {
		return weeklyActivityId;
	}

	public void setWeeklyActivityId(int weeklyActivityId) {
		this.weeklyActivityId = weeklyActivityId;
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
		return Objects.hash(date, netCalorie, netPoint, user, weeklyActivityId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeeklyActivity other = (WeeklyActivity) obj;
		return Objects.equals(date, other.date) && netCalorie == other.netCalorie && netPoint == other.netPoint
				&& Objects.equals(user, other.user) && weeklyActivityId == other.weeklyActivityId;
	}



	@Override
	public String toString() {
		return "WeeklyActivity [weeklyActivityId=" + weeklyActivityId + ", user=" + user + ", date=" + date
				+ ", netCalorie=" + netCalorie + ", netPoint=" + netPoint + "]";
	}

	

}
