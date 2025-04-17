package com.jobfinder.job_finder.entity.profile;

import com.jobfinder.job_finder.entity.User;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// Dùng để quản lý kế thừa theo bảng đơn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Tạo cột phân biệt loại
@DiscriminatorColumn(name = "profile_type", discriminatorType = DiscriminatorType.STRING)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String name;
    private String sdt;    
    private String email;
    private String image;
    private String address;
    // 1 user chỉ có 1 profile
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
