package com.jobfinder.job_finder.service.profile;

import com.jobfinder.job_finder.dto.profile.request.ProfileRequest;
import com.jobfinder.job_finder.entity.profile.Profile;

public interface ProfileService {
    Profile createProfile(ProfileRequest request);
    Profile updateProfile();
    Profile getProfile();
}
