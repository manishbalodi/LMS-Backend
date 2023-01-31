package com.lms.courseservice.service;

import java.util.Optional;

import java.util.List;
import com.lms.courseservice.dto.CourseDto;
import com.lms.courseservice.entity.Course;

public interface CourseService {

	Optional<CourseDto> getCourseById(long courseId);

	Optional<CourseDto> addCourse(CourseDto courseDto);

	void deleteCourse(long courseId);

	Optional<List<CourseDto>> getAllCourses();

	Optional<List<CourseDto>> getCoursesByEmail(String coachEmail);

}
