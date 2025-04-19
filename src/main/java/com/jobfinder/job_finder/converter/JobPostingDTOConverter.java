package com.jobfinder.job_finder.converter;

import com.jobfinder.job_finder.dto.JobPostingDTO;
import com.jobfinder.job_finder.dto.RecruiterDTO;
import com.jobfinder.job_finder.entity.JobPosting;
import com.jobfinder.job_finder.entity.Recruiter;
import com.jobfinder.job_finder.entity.Shift;
import com.jobfinder.job_finder.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobPostingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM");
    public JobPostingDTO toJobPostingDTO(JobPosting jobPosting) {
        JobPostingDTO jobPostingDTO = modelMapper.map(jobPosting, JobPostingDTO.class);
        Recruiter recruiter= (Recruiter) jobPosting.getRecruiter();
        jobPostingDTO.setRecruiter(recruiter.getCompanyName());
        List<Shift> shifts = jobPosting.getShifts();
        String shiftsResult = shifts.stream().map(shift -> shift.getName()+" : "
                +shift.getStartTime().format(formatter)+ " -> "
                +shift.getEndTime().format(formatter))
                .collect(Collectors.joining(", "));
        jobPostingDTO.setShifts(shiftsResult);
        return jobPostingDTO;
    }

}
