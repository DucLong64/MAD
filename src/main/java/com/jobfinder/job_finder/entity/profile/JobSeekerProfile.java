package com.jobfinder.job_finder.entity.profile;

import java.util.Date;

import com.jobfinder.job_finder.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User jobSeeker;
}
