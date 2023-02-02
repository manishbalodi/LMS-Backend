package com.lms.userservice.service;

import java.util.List;
import java.util.Optional;

import com.lms.userservice.dto.CourseDto;
import com.lms.userservice.dto.UserDto;
import com.lms.userservice.exception.UserException;

public interface UserService {
	
	public Optional<UserDto> registerUser(UserDto user) throws UserException;

	public List<CourseDto> getAllCourses() throws UserException;

}
