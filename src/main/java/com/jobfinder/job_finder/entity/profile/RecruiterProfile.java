package com.jobfinder.job_finder.entity.profile;

import jakarta.persistence.Entity;
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

}
