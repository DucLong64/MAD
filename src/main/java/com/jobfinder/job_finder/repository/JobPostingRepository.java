package com.jobfinder.job_finder.repository;

import com.jobfinder.job_finder.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    List<JobPosting> findByRecruiterId(Long recruiterId);  // Tìm các tin tuyển dụng của nhà tuyển dụng
    Optional<JobPosting> findByIdAndRecruiterId(Long jobId, Long recruiterId);  // Tìm tin tuyển dụng theo id và nhà tuyển dụng
    Optional<JobPosting> findById(Long jobId);
    List<JobPosting> findByisActiveTrue();
}