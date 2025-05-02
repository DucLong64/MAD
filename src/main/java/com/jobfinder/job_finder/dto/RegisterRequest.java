package com.jobfinder.job_finder.dto;

import com.jobfinder.job_finder.util.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
