package com.jobfinder.job_finder.service;

import com.jobfinder.job_finder.repository.ShiftRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;
    @Transactional
    public void deleteAllShiftsByJobId(Long jobId) {
        shiftRepository.deleteByJobPostingId(jobId);
    }
}
