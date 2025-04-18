package com.jobfinder.job_finder.repository.profile;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobfinder.job_finder.entity.profile.JobSeekerProfile;

@Repository
public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Long> {
    Optional<JobSeekerProfile> findByJobSeekerId(Long jobSeekerId); 
} 
