package com.lms.userservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.userservice.dto.ApiResponse;
import com.lms.userservice.dto.CourseDto;
import com.lms.userservice.dto.UserDto;
import com.lms.userservice.exception.UserException;
import com.lms.userservice.security.model.JwtRequest;
import com.lms.userservice.security.model.JwtResponse;
import com.lms.userservice.security.service.JwtService;
import com.lms.userservice.security.util.JwtUtil;
import com.lms.userservice.service.CoachService;
import com.lms.userservice.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
	
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
	@Autowired
	CoachService coachService;
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDto userDto) throws UserException{
    	Optional<UserDto> user = userService.registerUser(userDto);
    	if(user.isPresent()) {
    		UserDto returnedUser = user.get();
    		returnedUser.setUserPassword("********");
    		return new ResponseEntity<>(new ApiResponse(200, "User Created", user.get()),HttpStatus.OK);
    	}
		return new ResponseEntity<>(new ApiResponse(400, "User not created/some error occured", null),HttpStatus.OK);
    }
    
    @PostMapping("/login")
	public ResponseEntity<ApiResponse> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        return new ResponseEntity<>(new ApiResponse(200, "Logged In Sucessfully", jwtService.createJwtToken(jwtRequest)),HttpStatus.OK);
    }
    
    @GetMapping("/allCourses")
    public ResponseEntity<ApiResponse> getAllCourses() throws UserException{
    	List<CourseDto> courseList = userService.getAllCourses();
		return new ResponseEntity<>(new ApiResponse(200, "Course List", courseList),HttpStatus.OK);

    }
    
    @GetMapping("/searchCourse")
	public ResponseEntity<ApiResponse> searchCourse(@RequestParam String courseCategory,
			@RequestParam String courseDuration) throws UserException{
		List<CourseDto> courseList = coachService.searchCourse(courseCategory,courseDuration);
		return new ResponseEntity<>(new ApiResponse(200, "Course List", courseList),HttpStatus.OK);
	}

}
