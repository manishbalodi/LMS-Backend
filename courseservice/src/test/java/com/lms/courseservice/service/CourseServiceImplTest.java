package com.lms.courseservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
	
	@Test
	public void addCourseTest(){
		Course course = new Course(1,"manish course",5,"ydfughjg","vhjhjj","hjgjhgj","bbbb");
		when(courseRepository.save(Mockito.any())).thenReturn(course);
		CourseDto courseDto = new CourseDto(1,"manish course",5,"ydfughjg","vhjhjj","hjgjhgj","bbbb");
		Optional<CourseDto> c = courseServiceImpl.addCourse(courseDto);
		assertNotNull(c);
	}
	
	@Test
	public void deleteCourseTest(){
//		Course course = new Course(1,"manish course",5,"ydfughjg","vhjhjj","hjgjhgj","bbbb");
//		Mockito.when(courseRepository.deleteById(Mockito.anyLong()));
//		when(courseRepository.deleteById(Mockito.anyLong())).thenReturn();
//		courseRepository.deleteById((long) 1);
		courseServiceImpl.deleteCourse(1);
//		verify(courseRepository).deleteById(1);
		Mockito.verify(courseRepository).deleteById((long) 1);
//		CourseDto courseDto = new CourseDto(1,"manish course",5,"ydfughjg","vhjhjj","hjgjhgj","bbbb");
//		Optional<CourseDto> c = courseServiceImpl.addCourse(courseDto);
//		assertNotNull(c);
	}
	
	@Test
	public void getAllCourses(){
		List<Course> courseList = new ArrayList<>();
		courseList.add(new Course(1,"manish course",5,"ydfughjg","vhjhjj","hjgjhgj","bbbb"));
		courseList.add(new Course(2,"manish course",5,"ydfughjg","vhjhjj","hjgjhgj","bbbb"));
		Mockito.when(courseRepository.findAll()).thenReturn(courseList);
		Optional<List<CourseDto>> dtoList = courseServiceImpl.getAllCourses();
		assertEquals(2, dtoList.get().size());
		
	}
	
}
