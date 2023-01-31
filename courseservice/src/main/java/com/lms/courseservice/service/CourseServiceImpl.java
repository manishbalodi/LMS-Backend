package com.lms.courseservice.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.courseservice.dto.CourseDto;
import com.lms.courseservice.entity.Course;
import com.lms.courseservice.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Optional<CourseDto> getCourseById(long courseId) {
		Optional<Course> course = courseRepo.findById(courseId);
		Optional<CourseDto> courseDto = modelMapper.map(course, Optional.class);
		return courseDto;
	}

	@Override
	public Optional<CourseDto> addCourse(CourseDto courseDto) {
		Optional<Course> course = Optional.of(courseRepo.save(modelMapper.map(courseDto, Course.class)));
		Optional<CourseDto> savedCourseDto = modelMapper.map(course, Optional.class);
		return savedCourseDto;
	}

	@Override
	public void deleteCourse(long courseId) {
		courseRepo.deleteById(courseId);
	}

	@Override
	public Optional<List<CourseDto>> getAllCourses() {
		Optional<List<Course>> courses = Optional.of(courseRepo.findAll());
		Optional<List<CourseDto>> coursesDto = modelMapper.map(courses, Optional.class);
		return coursesDto;
	}

	@Override
	public Optional<List<CourseDto>> getCoursesByEmail(String coachEmail) {
		Optional<List<Course>> courses = courseRepo.findByCreatedBy(coachEmail);
		Optional<List<CourseDto>> coursesDto = modelMapper.map(courses, Optional.class);
		return coursesDto;
	}

}
