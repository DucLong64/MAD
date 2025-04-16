package com.jobfinder.job_finder.dto.profile.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class CreateRecruiterProfileRequest implements ProfileRequest {

    private String companyName;

    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String contactPhoneNumber;
    
    @Email(message = "Email should be valid")
    private String contactEmail;
    private String companyLogoUrl;
    private String companyAddress;
    private String companyDescription;
    private String companyOperationField;
    private String companyWebsite;
    
    // Getters and Setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyOperationField() {
        return companyOperationField;
    }

    public void setCompanyOperationField(String companyOperationField) {
        this.companyOperationField = companyOperationField;
    }

}
