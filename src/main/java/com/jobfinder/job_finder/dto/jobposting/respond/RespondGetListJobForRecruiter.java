package com.jobfinder.job_finder.dto.jobposting.respond;

//Interface Projection 
public interface RespondGetListJobForRecruiter {
    Long getId();
    String getTitle();
    String getLocation();
    String getApplicationDeadline();
    boolean getStatus();
    Integer getQuantity();
    Integer getAppliedCount();
}
