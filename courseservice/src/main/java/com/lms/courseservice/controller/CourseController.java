package com.lms.courseservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.courseservice.dto.ApiResponse;
import com.lms.courseservice.dto.CourseDto;
import com.lms.courseservice.entity.Course;
import com.lms.courseservice.exception.CourseException;
import com.lms.courseservice.service.CourseService;

@RestController
@RequestMapping("api/course")
@CrossOrigin
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/getCourse")
	public ResponseEntity<ApiResponse> getCourseById(@RequestParam long courseId) throws CourseException{
		Optional<CourseDto> course =  courseService.getCourseById(courseId);
		if(course.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(200, "Course Present", course.get()),HttpStatus.OK);
		}
		else {
			throw new CourseException("Course Not Found : course id-" +courseId);
		}
	}
	
	@GetMapping("/getCourses")
	public ResponseEntity<ApiResponse> getCoursesById(@RequestParam String userName) throws CourseException{
		Optional<List<CourseDto>> course =  courseService.getCoursesByUserName(userName);
		if(course.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(200, "Course List", course.get()),HttpStatus.OK);
		}
		else {
			throw new CourseException("Courses Not Found for : coach id-" +userName);
		}
	}
	
	@GetMapping("/searchCourses")
	public ResponseEntity<ApiResponse> searchCourses(@RequestParam String courseCategory,@RequestParam int courseDuration) throws CourseException{
		Optional<List<CourseDto>> course =  courseService.searchCourses(courseCategory,courseDuration);
		if(course.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(200, "Course List", course.get()),HttpStatus.OK);
		}
		else {
			throw new CourseException("Courses Not Found");
		}
	}
	
	@GetMapping("/allCourses")
	public ResponseEntity<ApiResponse> getAllCourses() throws CourseException{
		Optional<List<CourseDto>> course =  courseService.getAllCourses();
		if(course.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(200, "Courses List", course.get()),HttpStatus.OK);
		}
		else {
			throw new CourseException("No Courses found");
		}
	}
	
	
	@PostMapping("/addCourse")
	public ResponseEntity<ApiResponse> addCourse(@Valid @RequestBody CourseDto courseDto) throws CourseException{
		courseDto.setCourseId(0);
		Optional<CourseDto> course =  courseService.addCourse(courseDto);
		if(course.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(200, "Course Added", course.get()),HttpStatus.OK);
		}
		else {
			throw new CourseException("Course Not Saved : course id-" +courseDto.getCourseName());
		}
	}
	
	@DeleteMapping("/deleteCourse")
	public ResponseEntity<ApiResponse> deleteCourse(@RequestParam long courseId) throws CourseException{
		Optional<CourseDto> course =  courseService.getCourseById(courseId);
		if(course.isPresent()) {
			courseService.deleteCourse(courseId);
			return new ResponseEntity<>(new ApiResponse(200, "Course Deleted , course id-" + courseId, course.get()),HttpStatus.OK);
		}
		else {
			throw new CourseException("Can't delete , course id- " + courseId + " not present");
		}
	}
	
	
	
	

}
