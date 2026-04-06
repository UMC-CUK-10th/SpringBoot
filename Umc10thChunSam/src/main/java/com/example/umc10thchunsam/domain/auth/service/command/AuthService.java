package com.example.umc10thchunsam.domain.auth.service.command;

import com.example.umc10thchunsam.domain.auth.dto.req.SignupReq;
import com.example.umc10thchunsam.domain.auth.dto.res.SignupRes;
import com.example.umc10thchunsam.domain.auth.exception.AuthException;
import com.example.umc10thchunsam.domain.auth.exception.code.AuthErrorCode;
import com.example.umc10thchunsam.domain.member.entity.Member;
import com.example.umc10thchunsam.domain.member.repo.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class AuthService {


    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public SignupRes signup(SignupReq request) {

        if (memberRepository.existsByUserId(request.getUsername())) {
            throw new AuthException(AuthErrorCode.ID_Exists);
            //아이디 중복
        }

        Member member = Member.builder()
                .userId(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .adress(request.getAddress())
                .name(request.getName())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .phone(request.getPhone())
                .email(request.getEmail())
                .status("ACTIVE")
                .vaildate(null)              // 유효일 지나면 자동 삭제 염두
                .build();

        memberRepository.save(member);

        return new SignupRes(true);
    }
}
