package com.jobfinder.job_finder.dto;

import java.time.LocalDateTime;

public class ApplicationDTO {
    private Long idApplication;
    private Long idJobSeeker;
    private Long idJobPosting;
    private LocalDateTime applicationDate;
    private String status;

    // Constructor
    public ApplicationDTO(Long idApplication, Long idJobSeeker, Long idJobPosting, LocalDateTime applicationDate, String status) {
        this.idApplication = idApplication;
        this.idJobSeeker = idJobSeeker;
        this.idJobPosting = idJobPosting;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public Long getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(Long idApplication) {
        this.idApplication = idApplication;
    }

    public Long getIdJobSeeker() {
        return idJobSeeker;
    }

    public void setIdJobSeeker(Long idJobSeeker) {
        this.idJobSeeker = idJobSeeker;
    }

    public Long getIdJobPosting() {
        return idJobPosting;
    }

    public void setIdJobPosting(Long idJobPosting) {
        this.idJobPosting = idJobPosting;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
