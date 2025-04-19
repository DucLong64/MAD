package com.jobfinder.job_finder.service;

import com.jobfinder.job_finder.converter.JobPostingDTOConverter;
import com.jobfinder.job_finder.dto.JobPostingDTO;
import com.jobfinder.job_finder.entity.JobPosting;
import com.jobfinder.job_finder.entity.Shift;
import com.jobfinder.job_finder.repository.JobPostingRepository;
import com.jobfinder.job_finder.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {
    @Autowired
    private JobPostingRepository jobPostingRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private JobPostingDTOConverter jobPostingDTOConverter;

    public JobPostingDTO createJobPosting(JobPosting jobPosting, List<Shift> shifts) {
        jobPosting.setPostDate(java.time.LocalDateTime.now());
        jobPosting.setActive(true);
        JobPosting savedJobPosting = jobPostingRepository.save(jobPosting);

        if( shifts != null && !shifts.isEmpty()) {
            for(Shift shift : shifts) {
                shift.setJobPosting(savedJobPosting);
                shiftRepository.save(shift);
            }
        }
        return jobPostingDTOConverter.toJobPostingDTO(savedJobPosting);
    }
    // Tìm kiếm tin tuyển dụng của nhà tuyển dụng
    public List<JobPostingDTO> getJobPostings(Long recruiterId) {
        List<JobPosting> jobPostings = jobPostingRepository.findByRecruiterId(recruiterId);
        List<JobPostingDTO> jobPostingDTOS= new ArrayList<>();
        for(JobPosting jobPosting : jobPostings) {
            JobPostingDTO jobDTO = jobPostingDTOConverter.toJobPostingDTO(jobPosting);
            jobPostingDTOS.add(jobDTO);
        }
        return jobPostingDTOS;
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
    public List<JobPostingDTO> getAllJobPostings() {
        List<JobPosting> jobPostings = jobPostingRepository.findAll();
        List<JobPostingDTO> jobPostingDTOS= new ArrayList<>();
        for(JobPosting jobPosting : jobPostings) {
            JobPostingDTO jobDTO = jobPostingDTOConverter.toJobPostingDTO(jobPosting);
            jobPostingDTOS.add(jobDTO);
        }
        return jobPostingDTOS;
    }
    public List<JobPostingDTO> getAllJobPostingsAndActiveTure() {
        List<JobPosting> jobPostings = jobPostingRepository.findByisActiveTrue();
        List<JobPostingDTO> jobPostingDTOS= new ArrayList<>();
        for(JobPosting jobPosting : jobPostings) {
            JobPostingDTO jobDTO = jobPostingDTOConverter.toJobPostingDTO(jobPosting);
            jobPostingDTOS.add(jobDTO);
        }
        return jobPostingDTOS;
    }

}
