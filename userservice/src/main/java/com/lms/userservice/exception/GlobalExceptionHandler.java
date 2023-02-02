package com.lms.userservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lms.userservice.dto.ApiResponse;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ApiResponse> handleCourseException(UserException ex){
		ResponseEntity<ApiResponse> response = new ResponseEntity<>(new ApiResponse(400,ex.getMessage(),null),HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
		ResponseEntity<ApiResponse> response = new ResponseEntity<>(new ApiResponse(400,"Field Validation Error/s",errors),HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiResponse> handleUsernameNotFoundException(UsernameNotFoundException ex){
		ResponseEntity<ApiResponse> response = new ResponseEntity<>(new ApiResponse(400,ex.getMessage(),null),HttpStatus.BAD_REQUEST);
		return response;
	}
	
}
