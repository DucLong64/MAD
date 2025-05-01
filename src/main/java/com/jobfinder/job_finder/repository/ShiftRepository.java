package com.jobfinder.job_finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfinder.job_finder.entity.job.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {


}
