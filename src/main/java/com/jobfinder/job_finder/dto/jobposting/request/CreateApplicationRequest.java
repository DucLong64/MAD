package com.jobfinder.job_finder.dto.jobposting.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateApplicationRequest {
    private Long jobID;
    private List<Long> shiftID;
    private Long jobSeekerID;
}
