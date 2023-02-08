package com.lms.courseservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.courseservice.dto.CourseDto;
import com.lms.courseservice.entity.Course;
import com.lms.courseservice.repository.CourseRepository;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTest {
	
	@InjectMocks
	private CourseServiceImpl courseServiceImpl;
	
	@Mock
	private CourseRepository courseRepository;
	
	@Spy
    @Qualifier("myEntityMapper")
	private ModelMapper modelMapper;
	

	@Test
	public void getCourseByIdTest(){
		Optional<Course> course = Optional.of(new Course(1,"manish course",5,"ydfughjg","vhjhjj","hjgjhgj","bbbb"));
		when(courseRepository.findById(Mockito.anyLong())).thenReturn(course);
		Optional<CourseDto> c = courseServiceImpl.getCourseById(1);
		assertNotNull(c);
	}
}
