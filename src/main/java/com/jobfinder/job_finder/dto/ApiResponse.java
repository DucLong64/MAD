package com.jobfinder.job_finder.dto;

public class ApiResponse {
    private String status;
    private String message;
    private int errorCode;

    public ApiResponse(String status, String message, int errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }

    // Getters và Setters
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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
