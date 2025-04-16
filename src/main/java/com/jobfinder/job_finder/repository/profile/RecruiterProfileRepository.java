package com.jobfinder.job_finder.repository.profile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jobfinder.job_finder.entity.profile.RecruiterProfile;

@Repository
public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Long> {
    // Tìm kiếm hồ sơ nhà tuyển dụng theo ID nhà tuyển dụng
    Optional<RecruiterProfile> findByRecruiterId(Long recruiterId); 
} 