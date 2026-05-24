package com.example.springboot.global.security;

import com.example.springboot.domain.users.dto.UsersReqDTO;
import com.example.springboot.domain.users.dto.UsersResDTO;
import com.example.springboot.domain.users.service.UsersService;
import com.example.springboot.global.apiPayload.ApiResponse;
import com.example.springboot.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UsersService usersService;

    @PostMapping("/login")
    public ApiResponse<UsersResDTO.LoginResultDTO> login(
            @Valid @RequestBody UsersReqDTO.LoginDTO request
    ) {
        UsersResDTO.LoginResultDTO result = usersService.login(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
