package com.example.umc10th.domain.user.dto;
import lombok.Data;

import java.util.List;
@Data

public class UserReqDTO {

    public record SignUp(
            String email,
            String password,
            String nickname,
            String gender,
            String birthDate,
            String address,
            List<String> preferredFoods

    ) {}

    public record UpdateUser(
            String nickname
    ) {}
}