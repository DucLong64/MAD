package com.jobfinder.job_finder.entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public enum Role {
    ADMIN,            // Quản trị viên
    JOB_SEEKER,       // Người tìm việc
    RECRUITER         // Nhà tuyển dụng
}