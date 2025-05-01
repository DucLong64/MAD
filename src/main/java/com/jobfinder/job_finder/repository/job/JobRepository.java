package com.jobfinder.job_finder.repository.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobfinder.job_finder.dto.jobposting.respond.RespondGetListJobForRecruiter;
import com.jobfinder.job_finder.dto.jobposting.respond.RespondGetListJobForSeeker;
import com.jobfinder.job_finder.entity.job.Job;

import java.util.*;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByRecruiterId(Long recruiterId); // Tìm các tin tuyển dụng của nhà tuyển dụng

    Optional<Job> findByIdAndRecruiterId(Long jobId, Long recruiterId); // Tìm tin tuyển dụng theo id và nhà tuyển dụng

    List<Job> findByisActiveTrue();

    @Query("""
                SELECT jp.id AS id,
                       jp.title AS title,
                       jp.location AS address,
                       jp.applicationDeadline AS applicationDeadline,
                       jp.isActive AS status,
                       jp.quantity AS quantity,
                       COUNT(ss.id) AS appliedCount
                FROM Job jp
                LEFT JOIN jp.shifts s
                LEFT JOIN s.shiftJobSeekers ss
                WHERE jp.recruiter.id = :recruiterId
                GROUP BY jp.id, jp.title, jp.location, jp.applicationDeadline, jp.quantity
            """)
    List<RespondGetListJobForRecruiter> getJobPostingWithAppliedCount(@Param("recruiterId") Long recruiterId);

    // tìm các bài đăng đang mở
    @Query("""
                SELECT jp.id AS id,
                       jp.title AS title,
                       jp.location AS location,
                       jp.salary AS salary,
                       jp.description AS description,
                       jp.applicationDeadline AS applicationDeadline
                FROM Job jp
                WHERE jp.isActive = OPEN
            """)
    List<RespondGetListJobForSeeker> getAllJobPostingsForSeeker();
}