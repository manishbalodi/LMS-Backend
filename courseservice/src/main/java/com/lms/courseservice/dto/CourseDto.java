package com.lms.courseservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourseDto {
	
	private long courseId;
	@NotBlank(message = "Course Name can't be empty")
	@Size(min = 10,message = "Course Name should be minimum 10 characters")
	private String courseName;
//	@NotBlank(message = "Course Duration can't be empty")
	@NotNull(message = "Course Duration can't be empty")
	private int courseDuration;
	@NotBlank(message = "Course Description can't be empty")
	@Size(min = 100,message = "Course Description should be minimum 100 characters")
	private String courseDescription;
	@NotBlank(message = "Course Technology can't be empty")
	private String courseTechnology;
	@NotBlank(message = "Course LaunchUrl can't be empty")
	private String courseLaunchUrl;
	private String createdBy;
	
	public CourseDto() {
		super();
	}
	
	public CourseDto(long courseId, String courseName, int courseDuration, String courseDescription,
			String courseTechnology, String courseLaunchUrl, String createdBy) {
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
