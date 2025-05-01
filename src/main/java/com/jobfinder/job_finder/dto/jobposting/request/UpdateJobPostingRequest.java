package com.jobfinder.job_finder.dto.jobposting.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJobPostingRequest {
    @NotBlank(message = "Tiêu đề không được để trống")
    public String title;
    @NotBlank(message = "Mô tả công việc không được để trống")
    public String jobDescription;
    @NotBlank(message = "Địa điểm không được để trống")
    public String location;
    @NotBlank(message = "Yêu cầu ứng viên không được để trống")
    public String candidateRequirements;
    @NotBlank(message = "Lương không được để trống")
    public String salary;
    public String benefits;
    @NotBlank(message = "Số lượng không được để trống")
    public Integer quantity;
    @NotBlank(message = "Hạn đăng tuyển không được để trống")
    @FutureOrPresent(message = "Hạn đăng tuyển phải là ngày hiện tại hoặc tương lai")
    public LocalDate applicationDeadline;
}
