package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class MemberReqDTO {

    public static class SignUpDTO {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
        private String password;

        @NotBlank(message = "닉네임은 필수입니다.")
        private String nickname;

        private Gender gender;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birth;

        private Address address;

        private String detailAddress;

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getNickname() {
            return nickname;
        }

        public Gender getGender() {
            return gender;
        }

        public LocalDate getBirth() {
            return birth;
        }

        public Address getAddress() {
            return address;
        }

        public String getDetailAddress() {
            return detailAddress;
        }
    }

    public static class LoginDTO {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}