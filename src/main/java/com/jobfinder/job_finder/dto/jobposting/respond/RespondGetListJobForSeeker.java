package com.jobfinder.job_finder.dto.jobposting.respond;

public interface RespondGetListJobForSeeker {
    Long getId();
    String getTitle();
    String getLocation();
    String getSalary();
    String getDescription();
    String getApplicationDeadline();
}
