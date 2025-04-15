package com.jobfinder.job_finder.service;

import com.jobfinder.job_finder.entity.JobPosting;
import com.jobfinder.job_finder.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {
    @Autowired
    private JobPostingRepository jobPostingRepository;
    public JobPosting createJobPosting(JobPosting jobPosting) {
        jobPosting.setPostDate(java.time.LocalDateTime.now());
        jobPosting.setActive(true);
        return (JobPosting) jobPostingRepository.save(jobPosting);
    }
    // Tìm kiếm tin tuyển dụng của nhà tuyển dụng
    public List<JobPosting> getJobPostings(Long recruiterId) {
        return jobPostingRepository.findByRecruiterId(recruiterId);
    }
    // Cập nhật tin tuyển dụng
    public JobPosting updateJobPosting(Long jobId, Long recruiterId, JobPosting jobPosting) {
        Optional<JobPosting> existingJob = jobPostingRepository.findByIdAndRecruiterId(jobId, recruiterId);
        if (existingJob.isPresent()) {
            JobPosting updatedJob = existingJob.get();
            updatedJob.setTitle(jobPosting.getTitle());
            updatedJob.setDescription(jobPosting.getDescription());
            updatedJob.setLocation(jobPosting.getLocation());
            updatedJob.setContactEmail(jobPosting.getContactEmail());
            return (JobPosting) jobPostingRepository.save(updatedJob);
        } else {
            throw new RuntimeException("Job posting not found or does not belong to this recruiter");
        }
    }
    // Hủy tin tuyển dụng
    public void deleteJobPosting(Long jobId) {
        Optional<JobPosting> existingJob = jobPostingRepository.findById(jobId);
        if (existingJob.isPresent()) {
            JobPosting jobPosting = existingJob.get();
            jobPosting.setActive(false);  // Cập nhật trạng thái thành hủy
            jobPostingRepository.save(jobPosting);
        } else {
            throw new RuntimeException("Job posting not found or does not belong to this recruiter");
        }
    }
    // Lấy tất cả các tin tuyển dụng của tất cả nhà tuyển dụng
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();  // Trả về tất cả các tin tuyển dụng
    }
    public List<JobPosting> getAllJobPostingsAndActiveTure() {
        return jobPostingRepository.findByisActiveTrue();  // Trả về tất cả các tin tuyển dụng
    }

}
