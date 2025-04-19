package com.jobfinder.job_finder.repository;

import com.jobfinder.job_finder.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
