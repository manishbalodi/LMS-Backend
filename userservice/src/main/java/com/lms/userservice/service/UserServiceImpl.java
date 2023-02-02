package com.lms.userservice.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lms.userservice.dto.ApiResponse;
import com.lms.userservice.dto.CourseDto;
import com.lms.userservice.dto.UserDto;
import com.lms.userservice.entity.User;
import com.lms.userservice.exception.UserException;
import com.lms.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${baseUrl}")
	private String baseUrl;

	@Override
	public Optional<UserDto> registerUser(UserDto userDto) throws UserException {
		boolean isUserIdPresent = isUserPresent(userDto);
    	if(isUserIdPresent) {
    		throw new UserException("User Name already exists");
    	}
    	boolean isEmailIdPresent = isEmailPresent(userDto);
    	if(isEmailIdPresent) {
    		throw new UserException("Email Id already in use");
    	}
		User user = (modelMapper.map(userDto, User.class));
		user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		Optional<UserDto> savedUser = Optional.of(modelMapper.map(userRepository.save(user), UserDto.class));
		return savedUser;
	}

	public boolean isUserPresent(UserDto userDto) {
		User user = (modelMapper.map(userDto, User.class));
		boolean isUserIdPresent = userRepository.existsById(user.getUserName());
		return isUserIdPresent;
	}

	public boolean isEmailPresent(UserDto userDto) {
//		User user = (modelMapper.map(userDto, User.class));
		boolean isEmailIdPresent = false;
		List<User> userList = userRepository.findByUserEmail(userDto.getUserEmail());
		if(userList.size()>0) {
			isEmailIdPresent = true;
		}
		return isEmailIdPresent;
	}

	@Override
	public List<CourseDto> getAllCourses() throws UserException {
		ResponseEntity<ApiResponse> response = restTemplate.getForEntity(baseUrl+"/api/course/allCourses", ApiResponse.class);
		if(response.getStatusCodeValue()==200) {
			ApiResponse apiResponse = response.getBody();
			return (List<CourseDto>) apiResponse.getData();
		}else {
			throw new UserException("No courses found/error ocurred");
		}
	}

}
