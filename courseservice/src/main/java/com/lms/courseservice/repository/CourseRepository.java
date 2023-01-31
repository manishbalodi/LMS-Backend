package com.lms.courseservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.courseservice.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

//	@Query("select * from course where created_by=")
	Optional<List<Course>> findByCreatedBy(String coachEmail);

}
