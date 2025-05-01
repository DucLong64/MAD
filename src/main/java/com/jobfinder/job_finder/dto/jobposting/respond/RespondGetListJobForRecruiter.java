package com.jobfinder.job_finder.dto.jobposting.respond;

import com.jobfinder.job_finder.util.JobStatus;

//Interface Projection 
public interface RespondGetListJobForRecruiter {
    Long getId();
    String getTitle();
    String getLocation();
    String getApplicationDeadline();
    JobStatus getStatus();
    Integer getQuantity();
    Integer getAppliedCount();
}
