package com.lms.userservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.userservice.dto.ApiResponse;
import com.lms.userservice.dto.CourseDto;
import com.lms.userservice.exception.UserException;
import com.lms.userservice.service.CoachService;

@RestController
@RequestMapping("/api/coach")
public class CoachController {
	
	@Autowired
	CoachService coachService;
	
	@PreAuthorize("hasRole('ROLE_COACH')")
	@GetMapping("/allCourses")
	public ResponseEntity<ApiResponse> allCoursesByUserName(@RequestParam String userName,
			@RequestHeader(name="Authorization") String token) throws UserException{
		List<CourseDto> courseList = coachService.allCoursesByUserName(userName);
		return new ResponseEntity<>(new ApiResponse(200, "Course List", courseList),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_COACH')")
	@DeleteMapping("/deleteCourse")
	public ResponseEntity<ApiResponse> deleteCourse(@RequestParam long courseId,
			@RequestHeader(name="Authorization") String token) throws UserException{
		coachService.deleteCourse(courseId);
		return new ResponseEntity<>(new ApiResponse(200, "Course Deleted", courseId),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_COACH')")
	@PostMapping("/addCourse")
	public ResponseEntity<ApiResponse> addCourse(@Valid @RequestBody CourseDto courseDto,
			@RequestHeader(name="Authorization") String token) throws UserException{
		CourseDto savedCourse = coachService.addCourse(courseDto);
		return new ResponseEntity<>(new ApiResponse(200, "Course Added", savedCourse),HttpStatus.OK);
	}

}
