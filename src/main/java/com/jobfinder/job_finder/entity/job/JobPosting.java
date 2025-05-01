package com.jobfinder.job_finder.entity.job;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jobfinder.job_finder.dto.jobposting.request.CreateJobPostingRequest;
import com.jobfinder.job_finder.entity.User;

@Getter
@Setter
@Entity
public class JobPosting {
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
    ArrayList<Shift> shifts = new ArrayList<>(); // Danh sách ca làm việc

    private LocalDate applicationDeadline; // hạn đăng tuyển

    private boolean isActive; // trạng thái đăng tuyển
    private LocalDate createdAt; // ngày tạo bài đăng

    // Mối quan hệ N - 1 với Recruiter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id")
    private User recruiter;

    public JobPosting(CreateJobPostingRequest request) {
        this.title = request.title;
        this.jobDescription = request.jobDescription;
        this.location = request.location;
        this.candidateRequirements = request.candidateRequirements;
        this.salary = request.salary;
        this.benefits = request.benefits;
        this.quantity = request.quantity;
        this.applicationDeadline = request.applicationDeadline;
        this.shifts = new ArrayList<>(); // Khởi tạo danh sách ca làm việc
        this.isActive = true; // Mặc định là bài đăng đang hoạt động
        this.createdAt = LocalDate.now(); // Ngày tạo bài đăng là ngày hiện tại
    }
}
