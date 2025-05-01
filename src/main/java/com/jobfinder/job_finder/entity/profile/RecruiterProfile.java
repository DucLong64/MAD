package com.jobfinder.job_finder.entity.profile;

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
// hồ sơ nhà tuyển dụng
public class RecruiterProfile extends Profile {

    private String description;
    private String operationField;
    private String link;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User recruiter;
}
