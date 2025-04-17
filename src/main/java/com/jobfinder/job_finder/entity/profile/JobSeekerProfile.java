package com.jobfinder.job_finder.entity.profile;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// hồ sơ người tìm việcviệc
public class JobSeekerProfile extends Profile{

    private Date dob;  
    private String gender;
    private String education;
    private String pathCV;
    private String language;
}
