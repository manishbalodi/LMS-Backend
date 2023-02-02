package com.lms.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.userservice.entity.CoachCourses;

public interface CoachCoachRepository extends JpaRepository<CoachCourses, Long>{

	void deleteByCourseId(long courseId);

}
