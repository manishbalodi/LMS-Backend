package com.lms.courseservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import com.lms.courseservice.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CourseException.class)
	public ResponseEntity<ApiResponse> handleCourseException(CourseException ex){
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

}
