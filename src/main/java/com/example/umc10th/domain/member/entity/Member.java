package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    Long id;
    String username;
    String password;
    Gender gender;
    String birth;
    String email;
    String phoneNumber;
    String address;
    String addressDetail;
}
