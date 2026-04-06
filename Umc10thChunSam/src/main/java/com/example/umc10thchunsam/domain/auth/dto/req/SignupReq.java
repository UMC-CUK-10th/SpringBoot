package com.example.umc10thchunsam.domain.auth.dto.req;


import com.example.umc10thchunsam.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class SignupReq {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotNull
    private Gender gender;
    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String address;

    @NotNull
    @Email
    private String email;
    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(
            regexp = "^(01[0|1|6|7|8|9])[-]?(\\d{3,4})[-]?(\\d{4})$",
            message = "전화번호 형식이 올바르지 않습니다. 예: 010-1234-5678"
    )
    private String phone;


}
