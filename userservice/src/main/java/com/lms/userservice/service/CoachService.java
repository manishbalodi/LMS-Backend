package com.lms.userservice.service;

import java.util.List;
import java.util.Optional;

import com.lms.userservice.dto.CourseDto;
import com.lms.userservice.exception.UserException;

public interface CoachService {

	List<CourseDto> allCoursesByUserName(String userName) throws UserException;

	boolean deleteCourse(long courseId) throws UserException;

	CourseDto addCourse(CourseDto courseDto) throws UserException;

}
