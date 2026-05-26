package com.example.springboot10th.domain.auth.converter;

import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.enums.Gender;

public class AuthConverter {

    /**
     * 회원가입 요청 DTO → User 엔티티 변환.
     * 비밀번호는 서비스 계층에서 BCrypt 인코딩 후 전달받는다.
     */
    public static User toUser(AuthRequestDTO.SignupRequest request, String encodedPassword) {
        // gender 문자열 → enum 변환 (null 또는 알 수 없는 값이면 NONE)
        Gender gender = parseGender(request.getGender());

        return User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName() != null ? request.getName() : "사용자")
                .nickname(request.getName() != null ? request.getName() : "사용자")
                .phoneNum("010-0000-0000")
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .gender(gender)
                .birthDate(request.getBirthDate())
                .point(0)
                .build();
    }

    // "남" → MALE, "여" → FEMALE, 나머지 → NONE
    private static Gender parseGender(String genderStr) {
        if (genderStr == null) return Gender.NONE;
        return switch (genderStr) {
            case "남", "MALE" -> Gender.MALE;
            case "여", "FEMALE" -> Gender.FEMALE;
            default -> Gender.NONE;
        };
    }


    public static AuthResponseDTO.SignupResponse toSignupResponse(User user) {
        return AuthResponseDTO.SignupResponse.builder()
                .memberId(user.getId())
                .build();
    }

    public static AuthResponseDTO.LoginResponse toLoginResponse(User user) {
        return AuthResponseDTO.LoginResponse.builder()
                .memberId(user.getId())
                .build();
    }

    public static AuthResponseDTO.LogoutResponse toLogoutResponse() {
        return AuthResponseDTO.LogoutResponse.builder()
                .memberId(1L)
                .build();
    }
}

