package com.jobfinder.job_finder.dto.profile.request;

import java.util.Date;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateJobSeekerProfileRequest {

    @NotBlank(message = "Tên đầy đủ là bắt buộc.")
    @Size(min = 3, max = 100, message = "Phải có ít nhất 3 kí tự")
    private String fullName;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ.")
    private Date dateOfBirth;

    @NotBlank(message = "Số điện thoại là bắt buộc.")
    @Pattern(regexp = "^(?:\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Số điện thoại không hợp lệ.")
    private String phoneNumber;

    @NotBlank(message = "Email là bắt buộc.")
    @Email(message = "Email không hợp lệ.")
    private String email;

    private String gender;

    @NotBlank(message = "Địa chỉ là bắt buộc.")
    @Size(max = 200, message = "Quá dài.")
    private String address;

    private String education;

    private String language;

    @URL(message = "Đường dẫn ảnh không hợp lệ.")
    private String imgUrl;

    @URL(message = "Đường dẫn CV không hợp lệ.")
    private String cvUrl;

}
