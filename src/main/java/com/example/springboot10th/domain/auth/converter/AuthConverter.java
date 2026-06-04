package com.example.springboot10th.domain.auth.converter;

import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.enums.Gender;

public class AuthConverter {

    



    public static User toUser(AuthRequestDTO.SignupRequest request, String encodedPassword) {
        
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

    
    private static Gender parseGender(String genderStr) {
        if (genderStr == null) return Gender.NONE;
        return switch (genderStr) {
            case "남", "MALE" -> Gender.MALE;
            case "여", "FEMALE" -> Gender.FEMALE;
            default -> Gender.NONE;
        };
    }


    public static AuthResponseDTO.SignupResponse toSignupResponse(User user, String accessToken) {
        return AuthResponseDTO.SignupResponse.builder()
                .memberId(user.getId())
                .accessToken(accessToken)
                .build();
    }

    public static AuthResponseDTO.LoginResponse toLoginResponse(User user, String accessToken) {
        return AuthResponseDTO.LoginResponse.builder()
                .memberId(user.getId())
                .accessToken(accessToken)
                .build();
    }

    public static AuthResponseDTO.LogoutResponse toLogoutResponse() {
        return AuthResponseDTO.LogoutResponse.builder()
                .memberId(1L)
                .build();
    }
}

