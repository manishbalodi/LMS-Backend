package com.lms.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CoachCourses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String coachUserName;
	private long courseId;
	
	public CoachCourses(int i, String coachUserName2, long courseId2) {
		// TODO Auto-generated constructor stub
	}
	public CoachCourses() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCoachUserName() {
		return coachUserName;
	}
	public void setCoachUserName(String coachUserName) {
		this.coachUserName = coachUserName;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
}

