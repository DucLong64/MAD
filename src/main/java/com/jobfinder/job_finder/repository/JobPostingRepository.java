package com.jobfinder.job_finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfinder.job_finder.entity.job.JobPosting;

import java.util.*;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    List<JobPosting> findByRecruiterId(Long recruiterId);  // Tìm các tin tuyển dụng của nhà tuyển dụng
    Optional<JobPosting> findByIdAndRecruiterId(Long jobId, Long recruiterId);  // Tìm tin tuyển dụng theo id và nhà tuyển dụng
    List<JobPosting> findByisActiveTrue();
}