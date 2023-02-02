package com.lms.userservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.userservice.dto.ApiResponse;
import com.lms.userservice.dto.CourseDto;
import com.lms.userservice.entity.CoachCourses;
import com.lms.userservice.exception.UserException;
import com.lms.userservice.repository.CoachCoachRepository;

@Service
public class CoachServiceImpl implements CoachService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${baseUrl}")
	private String baseUrl;
	
	@Autowired
	private CoachCoachRepository coachRepo;

	@Override
	public List<CourseDto> allCoursesByUserName(String userName) throws UserException {
		ResponseEntity<ApiResponse> response = restTemplate.getForEntity(baseUrl+"/api/course/getCourses?coachUserName="+userName, ApiResponse.class);
		if(response.getStatusCodeValue()==200) {
			return (List<CourseDto>) response.getBody().getData();
		}
		else {
			throw new UserException("No courses found/error ocurred");
		}
	}

	@Override
	@Transactional
	public boolean deleteCourse(long courseId) throws UserException {
		ResponseEntity<ApiResponse> response = restTemplate.exchange(baseUrl+"/api/course/deleteCourse?courseId="+courseId,HttpMethod.DELETE, null,ApiResponse.class);
		if(response.getStatusCodeValue()==200) {
			coachRepo.deleteByCourseId(courseId);
			return true;
		}
		if(response.getStatusCodeValue()==400) {
			throw new UserException(response.getBody().getMessage());
		}
		else {
			throw new UserException("Course Not deleted/error ocurred");
		}
		
	}

	@Override
	public CourseDto addCourse(CourseDto courseDto) throws UserException {
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<ApiResponse> response = restTemplate.postForEntity(baseUrl+"/api/course/addCourse", courseDto, ApiResponse.class);
		if(response.getStatusCodeValue()==200) {
			ApiResponse resApi = response.getBody();
			CourseDto coachCourse = mapper.convertValue(resApi.getData(), CourseDto.class);
			CoachCourses coachCoursesObj = new CoachCourses();
			coachCoursesObj.setCoachUserName(coachCourse.getCreatedBy());
			coachCoursesObj.setCourseId(coachCourse.getCourseId());
			coachRepo.save(coachCoursesObj);
			return coachCourse;
		}
		else {
		 throw new UserException("Course Not saved/error ocurred");
		}
	}

}
