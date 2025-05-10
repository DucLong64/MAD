package com.jobfinder.job_finder.converter;

import com.jobfinder.job_finder.dto.ApplicationDTO;
import com.jobfinder.job_finder.entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDTOConverter {
    public ApplicationDTO convert(Application application) {
        return new ApplicationDTO(
                application.getId(),
                application.getJobSeeker().getId(),
                application.getJobPosting().getId(),
                application.getApplicationDate(),
                application.getStatus().toString()
        );
    }
}
