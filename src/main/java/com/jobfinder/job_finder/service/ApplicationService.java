package com.jobfinder.job_finder.service;

import com.jobfinder.job_finder.entity.Application;
import com.jobfinder.job_finder.entity.JobPosting;
import com.jobfinder.job_finder.entity.JobSeeker;
import com.jobfinder.job_finder.repository.ApplicationRepository;
import com.jobfinder.job_finder.util.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application applyForJob(JobSeeker jobSeeker, JobPosting jobPosting) {
        Optional<Application> existingApplication = applicationRepository.findByJobSeekerAndJobPosting(jobSeeker,jobPosting);
        if (existingApplication.isPresent()) {
            throw new IllegalStateException("You have already applied for this job.");
        }
        Application application = new Application();
        application.setJobSeeker(jobSeeker);
        application.setJobPosting(jobPosting);
        application.setApplicationDate(LocalDateTime.now());
        application.setStatus(ApplicationStatus.PENDING);

        return applicationRepository.save(application);
    }
    //Phe duyet application cua ung vien
    public Application updateApplicationStatus(Long applicationId, ApplicationStatus status) {
        Optional<Application> application = applicationRepository.findById(applicationId);
        if (!application.isPresent()) {
            throw new RuntimeException("Ứng tuyển không tìm thấy");
        }

        Application existingApplication = application.get();
        existingApplication.setStatus(status);
        return applicationRepository.save(existingApplication);
    }
    public List<Application> getApplicationsByJobPosting(Long jobPostingId) {
        return applicationRepository.findByJobPostingId(jobPostingId);
    }
}
