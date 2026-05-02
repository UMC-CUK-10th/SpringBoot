package com.example.springboot10th.domain.auth.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

public class AuthRequestDTO {

    @Getter
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
        private String gender;
        private LocalDate birthDate;
        private String address;
        private String addressDetail;
        private List<String> preferredFoodTypes;
        private List<String> agreements;
    }

    @Getter
    public static class LoginRequest {
        private String email;
        private String password;
    }
}
