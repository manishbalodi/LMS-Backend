package com.lms.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	List<User> findByUserEmail(String userEmail);

}
