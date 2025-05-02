package com.jobfinder.job_finder.entity.job;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobfinder.job_finder.dto.jobposting.request.CreateJobPostingRequest;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.util.JobStatus;

@Getter
@Setter
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    private String location;

    @Column(columnDefinition = "TEXT")
    private String candidateRequirements;

    private String salary;

    @Column(columnDefinition = "TEXT")
    private String benefits;

    private Integer quantity;

    // Ca làm
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Shift> shifts = new ArrayList<>(); // Danh sách ca làm việc

    private LocalDate applicationDeadline; // hạn đăng tuyển

    @Enumerated(EnumType.STRING)
    private JobStatus isActive; // trạng thái đăng tuyển
    private LocalDate createdAt; // ngày tạo bài đăng

    // Mối quan hệ N - 1 với Recruiter
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "recruiter_id")
    private User recruiter;

    public Job() {
    }

    public Job(CreateJobPostingRequest request) {
        this.title = request.title;
        this.jobDescription = request.jobDescription;
        this.location = request.location;
        this.candidateRequirements = request.candidateRequirements;
        this.salary = request.salary;
        this.benefits = request.benefits;
        this.quantity = request.quantity;
        this.applicationDeadline = request.applicationDeadline;
        this.isActive = JobStatus.PENDING; // Mặc định là bài đăng chưa được duyệt
        this.createdAt = LocalDate.now(); // Ngày tạo bài đăng là ngày hiện tại
    }
}
