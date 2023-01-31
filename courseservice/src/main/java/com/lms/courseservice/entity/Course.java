package com.lms.courseservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	private String courseName;
	private int courseDuration;
	private String courseDescription;
	private String courseTechnology;
	private String courseLaunchUrl;
	private String createdBy;
	public Course() {
		super();
	}

	public Course(long courseId, String courseName, int courseDuration, String courseDescription,
			String courseTechnology, String courseLaunchUrl,String createdBy) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseDescription = courseDescription;
		this.courseTechnology = courseTechnology;
		this.courseLaunchUrl = courseLaunchUrl;
		this.createdBy = createdBy;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseTechnology() {
		return courseTechnology;
	}

	public void setCourseTechnology(String courseTechnology) {
		this.courseTechnology = courseTechnology;
	}

	public String getCourseLaunchUrl() {
		return courseLaunchUrl;
	}

	public void setCourseLaunchUrl(String courseLaunchUrl) {
		this.courseLaunchUrl = courseLaunchUrl;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
