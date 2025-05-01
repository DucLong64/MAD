package com.jobfinder.job_finder.entity.job;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.util.ApplyStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shift_jobseeker")
public class ShiftJobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User jobSeeker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @Enumerated(EnumType.STRING)
    private ApplyStatus status = ApplyStatus.PENDING; // Trạng thái của người tìm việc với ca làm việc này (PENDING, ACCEPTED, REJECTED)

    // Thời gian người tìm việc nộp đơn cho ca làm việc này
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime appliedAt;

}
