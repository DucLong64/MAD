package com.jobfinder.job_finder.util;

public class util {
    public static Role getRole(String role) {
        switch (role.toUpperCase()) {
            case "1":
                return Role.ADMIN;
            case "2":
                return Role.JOB_SEEKER;
            case "3":
                return Role.RECRUITER;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
