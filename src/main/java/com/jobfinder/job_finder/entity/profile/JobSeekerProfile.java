package com.jobfinder.job_finder.entity.profile;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// hồ sơ người tìm việcviệc
public class JobSeekerProfile {

    private String dob;  
    private String gender;
    private String attribute;
    private String education;
    private String pathCV;
    private String language;
}
