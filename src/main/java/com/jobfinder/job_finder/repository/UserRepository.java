package com.jobfinder.job_finder.repository;

import com.jobfinder.job_finder.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}