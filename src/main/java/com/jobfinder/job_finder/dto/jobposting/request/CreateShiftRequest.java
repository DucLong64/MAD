package com.jobfinder.job_finder.dto.jobposting.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShiftRequest {
    @NotBlank(message = "Ngày không được để trống")
    @FutureOrPresent(message = "Ngày làm việc phải là ngày hiện tại hoặc tương lai")
    public LocalDate date;
    @NotBlank(message = "Thời gian bắt đầu không được để trống")
    public LocalTime startTime;
    @NotBlank(message = "Thời gian kết thúc không được để trống")
    public LocalTime endTime;
    @Size(min = 1 , message = "Phải có ít nhất 1 người làm việc")
    public Integer requiredQuantity;
}
