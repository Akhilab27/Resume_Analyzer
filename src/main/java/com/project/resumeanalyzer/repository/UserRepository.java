package com.project.resumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.resumeanalyzer.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);
}