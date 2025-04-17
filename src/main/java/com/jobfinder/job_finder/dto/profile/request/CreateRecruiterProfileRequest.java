package com.jobfinder.job_finder.dto.profile.request;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateRecruiterProfileRequest {

    @NotBlank(message = "Phải có tên công ty.")
    @Size(min = 2, max = 100, message = "Tên công ty phải có ít nhất 2 ký tự.")
    private String companyName;

    @NotBlank(message = "Hãy cho mình biết số điện thoại nhé!")
    @Pattern(regexp = "^(?:\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Số điện thoại không hợp lệ.")
    private String contactPhoneNumber;

    @NotBlank(message = "Hãy cho tôi biết email của bạn!")
    @Email(message = "Email không hợp lệ rồi thằng ngu.")
    private String contactEmail;

    @URL(message = "Có thật là mày đưa tao ảnh không")
    private String companyLogoUrl;

    @NotBlank(message = "Điền cái địa chỉ vào.")
    @Size(max = 255, message = "Mày ở đâu mà địa chỉ dài vậy.")
    private String companyAddress;

    @Size(max = 500, message = "Mày mô tả công ty gì mà dài vậy đa cấp à.")
    private String companyDescription;

    @Size(max = 100, message = "đa cấp à mà lắm lĩnh vực vậy.")
    private String companyOperationField;

    @URL(message = "Mày đưa tao địa chỉ web lừa đảo à, đổi lại!")
    private String companyWebsite;
    
}
