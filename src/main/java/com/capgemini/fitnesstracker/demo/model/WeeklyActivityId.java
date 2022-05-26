package com.capgemini.fitnesstracker.demo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Embeddable
@Component
public class WeeklyActivityId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "email")
	private User user;

	private int week;

	private int month;

	private int year;

	public WeeklyActivityId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeeklyActivityId(User user, int week, int month, int year) {
		super();
		this.user = user;
		this.week = week;
		this.month = month;
		this.year = year;
	}

	@Override
	public String toString() {
		return "WeeklyActivityId [user=" + user + ", week=" + week + ", month=" + month + ", year=" + year + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(month, user, week, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeeklyActivityId other = (WeeklyActivityId) obj;
		return month == other.month && Objects.equals(user, other.user) && week == other.week && year == other.year;
	}

}
