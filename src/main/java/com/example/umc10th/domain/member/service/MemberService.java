package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final String kakaoUserInfoUri;
    private final RestClient restClient;

    public MemberService(
            MemberRepository memberRepository,
            JwtTokenProvider jwtTokenProvider,
            PasswordEncoder passwordEncoder,
            @Value("${oauth.kakao.user-info-uri}") String kakaoUserInfoUri
    ) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.kakaoUserInfoUri = kakaoUserInfoUri;
        this.restClient = RestClient.create();
    }

    public Member signUp(MemberReqDTO.SignUpDTO request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        if (memberRepository.existsByName(request.getNickname())) {
            throw new MemberException(MemberErrorCode.NICKNAME_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = MemberConverter.toMember(request, encodedPassword);

        return memberRepository.save(member);
    }

    public MemberResDTO.LoginResultDTO login(MemberReqDTO.LoginDTO request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.LOGIN_FAILED);
        }

        String accessToken = jwtTokenProvider.createAccessToken(member);

        return MemberConverter.toLoginResultDTO(member, accessToken);
    }

    public MemberResDTO.LoginResultDTO kakaoLogin(MemberReqDTO.OAuthLoginDTO request) {

        KakaoUserInfo kakaoUserInfo = getKakaoUserInfo(request.getAccessToken());

        Member member = memberRepository.findByKakaoId(kakaoUserInfo.kakaoId())
                .orElseGet(() -> memberRepository.findByEmail(kakaoUserInfo.email())
                        .orElseGet(() -> memberRepository.save(createKakaoMember(kakaoUserInfo))));

        String accessToken = jwtTokenProvider.createAccessToken(member);

        return MemberConverter.toLoginResultDTO(member, accessToken);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.MyPageResponseDTO getMyPage(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPageResponseDTO(member);
    }

    private Member createKakaoMember(KakaoUserInfo kakaoUserInfo) {

        String encodedPassword = passwordEncoder.encode("KAKAO_" + kakaoUserInfo.kakaoId());

        return new Member(
                kakaoUserInfo.email(),
                encodedPassword,
                kakaoUserInfo.nickname(),
                SocialType.KAKAO,
                kakaoUserInfo.kakaoId()
        );
    }

    @SuppressWarnings("unchecked")
    private KakaoUserInfo getKakaoUserInfo(String kakaoAccessToken) {

        try {
            Map<String, Object> response = restClient.get()
                    .uri(kakaoUserInfoUri)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + kakaoAccessToken)
                    .retrieve()
                    .body(Map.class);

            if (response == null || response.get("id") == null) {
                throw new MemberException(MemberErrorCode.LOGIN_FAILED);
            }

            Long kakaoId = ((Number) response.get("id")).longValue();

            Map<String, Object> kakaoAccount =
                    (Map<String, Object>) response.get("kakao_account");

            String email = null;
            String nickname = null;

            if (kakaoAccount != null) {
                Object emailObj = kakaoAccount.get("email");
                if (emailObj != null) {
                    email = emailObj.toString();
                }

                Object profileObj = kakaoAccount.get("profile");
                if (profileObj instanceof Map<?, ?> profileMap) {
                    Object nicknameObj = profileMap.get("nickname");
                    if (nicknameObj != null) {
                        nickname = nicknameObj.toString();
                    }
                }
            }

            if (email == null || email.isBlank()) {
                email = "kakao_" + kakaoId + "@kakao.oauth";
            }

            if (nickname == null || nickname.isBlank()) {
                nickname = "kakao_" + kakaoId;
            }

            nickname = makeUniqueNickname(nickname, kakaoId);

            return new KakaoUserInfo(kakaoId, email, nickname);

        } catch (RestClientException | ClassCastException e) {
            throw new MemberException(MemberErrorCode.LOGIN_FAILED);
        }
    }

    private String makeUniqueNickname(String nickname, Long kakaoId) {

        String base = nickname.replaceAll("\\s+", "");

        if (base.isBlank()) {
            base = "kakao";
        }

        if (base.length() > 20) {
            base = base.substring(0, 20);
        }

        if (!memberRepository.existsByName(base)) {
            return base;
        }

        String suffix = "_" + kakaoId;
        int maxBaseLength = 20 - suffix.length();

        if (maxBaseLength <= 0) {
            return String.valueOf(kakaoId).substring(0, 20);
        }

        if (base.length() > maxBaseLength) {
            base = base.substring(0, maxBaseLength);
        }

        return base + suffix;
    }

    private record KakaoUserInfo(
            Long kakaoId,
            String email,
            String nickname
    ) {
    }
}