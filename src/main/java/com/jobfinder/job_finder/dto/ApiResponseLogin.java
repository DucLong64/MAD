package com.jobfinder.job_finder.dto;


public class ApiResponseLogin {

    private String status;
    private String message;
    private UserDTOResponse data;

    public ApiResponseLogin(String status, String message, UserDTOResponse data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTOResponse getData() {
        return data;
    }

    public void setData(UserDTOResponse data) {
        this.data = data;
    }
}
