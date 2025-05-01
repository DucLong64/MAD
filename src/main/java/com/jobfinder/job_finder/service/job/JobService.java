package com.jobfinder.job_finder.service.job;

import com.jobfinder.job_finder.dto.jobposting.request.CreateJobPostingRequest;
import com.jobfinder.job_finder.dto.jobposting.request.CreateShiftRequest;
import com.jobfinder.job_finder.dto.jobposting.request.UpdateJobPostingRequest;
import com.jobfinder.job_finder.dto.jobposting.respond.RespondGetListJobForRecruiter;
import com.jobfinder.job_finder.dto.jobposting.respond.RespondGetListJobForSeeker;
import com.jobfinder.job_finder.entity.User;
import com.jobfinder.job_finder.entity.job.Job;
import com.jobfinder.job_finder.entity.job.Shift;
import com.jobfinder.job_finder.repository.UserRepository;
import com.jobfinder.job_finder.repository.job.JobRepository;
import com.jobfinder.job_finder.repository.job.ShiftRepository;
import com.jobfinder.job_finder.util.JobStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private final JobRepository jobRepository;
    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, ShiftRepository shiftRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.shiftRepository = shiftRepository;
        this.userRepository = userRepository;
    }

    // Tạo mới tin tuyển dụng
    // nhận thông tin từ request và id nhà tuyển dụng 
    // mặc mặc định trạng thái hoạt động là true và thời gian tạo lấy là lúc tạo
    // và trả về bài đăng mới
    public Job createJobPosting(CreateJobPostingRequest request, Long recruiterId) {
        Job job = new Job(request);
        List<CreateShiftRequest> shifts = request.getShifts();
        // Tạo các ca làm việc từ thông tin trong request
        for (CreateShiftRequest shiftRequest : shifts) {
            Shift shift = new Shift();
            shift.setStartTime(shiftRequest.getStartTime());
            shift.setEndTime(shiftRequest.getEndTime());
            shift.setJob(job);
            job.getShifts().add(shift);
            shiftRepository.save(shift); 
        }
        User user = userRepository.findById(recruiterId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        job.setRecruiter(user);
        return (Job) jobRepository.save(job);
    }

    // Tìm kiếm tin tuyển dụng của nhà tuyển dụng với các thông tin cần thiết
    public List<RespondGetListJobForRecruiter> getJobForRecruiter(Long recruiterId) {
        return jobRepository.getJobPostingWithAppliedCount(recruiterId);
    }

    // Lấy danh sách tất cả các tin tuyển dụng với các thông tin cần thiết
    public List<RespondGetListJobForSeeker> getAllJobPostings() {
        return jobRepository.getAllJobPostingsForSeeker(JobStatus.OPEN);  // Trả về tất cả các tin tuyển dụng
    }

    // Cập nhật tin tuyển dụng
    public Job updateJobPosting(Long jobId, Long recruiterId, UpdateJobPostingRequest request) {
        Job existingJob = jobRepository
                                .findByIdAndRecruiterId(jobId, recruiterId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài đăng tuyển dụng"));
        existingJob.setTitle(request.getTitle());
        existingJob.setJobDescription(request.getJobDescription());
        existingJob.setLocation(request.getLocation());
        existingJob.setCandidateRequirements(request.getCandidateRequirements());
        existingJob.setSalary(request.getSalary());
        existingJob.setBenefits(request.getBenefits());
        existingJob.setQuantity(request.getQuantity());
        existingJob.setApplicationDeadline(request.getApplicationDeadline());

        return jobRepository.save(existingJob);  // Lưu bài đăng đã cập nhật
    }

    // Chuyển trạng thái hoạt động của tin tuyển dụng
    public Job toggleJobPostingStatus(Long jobId, Long recruiterId, String status) {
        Job existingJob = jobRepository
                                .findByIdAndRecruiterId(jobId, recruiterId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài đăng tuyển dụng"));
        existingJob.setIsActive(status.equals("open") ? JobStatus.CLOSE : JobStatus.OPEN); // Chuyển trạng thái hoạt động
        return (Job) jobRepository.save(existingJob);  // trả lại bản tin
    }

    // Xóa tin tuyển dụng
    public void deleteJobPosting(Long jobId, Long recruiterId) {
        // Tìm kiếm bài đăng tuyển dụng theo ID và ID nhà tuyển dụng
        Job existingJob = jobRepository
                                .findByIdAndRecruiterId(jobId, recruiterId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài đăng tuyển dụng"));
        jobRepository.delete(existingJob);  // Xóa bài đăng tuyển dụng
    }

    public Job getJobPostings(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài đăng tuyển dụng"));
    }

    // // Lấy tất cả các tin tuyển dụng của tất cả nhà tuyển dụng
    // public List<JobPosting> getAllJobPostings() {
    //     return jobPostingRepository.findAll();  // Trả về tất cả các tin tuyển dụng
    // }
    

}
