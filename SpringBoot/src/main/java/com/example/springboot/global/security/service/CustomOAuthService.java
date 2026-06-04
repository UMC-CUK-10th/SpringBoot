package com.example.springboot.global.security.service;

import com.example.springboot.domain.users.converter.UsersConverter;
import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.entity.enums.SocialType;
import com.example.springboot.domain.users.repository.UsersRepository;
import com.example.springboot.global.security.dto.KakaoDTO;
import com.example.springboot.global.security.dto.OAuthDTO;
import com.example.springboot.global.security.entity.OAuthUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            OAuth2User oAuth2User = super.loadUser(userRequest);

            SocialType providerId = SocialType.valueOf(
                    userRequest.getClientRegistration().getRegistrationId().toUpperCase()
            );

            if (providerId != SocialType.KAKAO) {
                throw oauthError("지원하지 않는 소셜 로그인입니다.");
            }

            Object idAttr = oAuth2User.getAttribute("id");
            if (idAttr == null) {
                throw oauthError("카카오 사용자 id를 가져오지 못했습니다.");
            }
            String socialUid = idAttr.toString();

            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            if (kakaoAccount == null) {
                throw oauthError("kakao_account 정보가 없습니다. 카카오 로그인 동의항목(닉네임, 이메일)을 확인하세요.");
            }

            Object emailObj = kakaoAccount.get("email");
            if (emailObj == null) {
                throw oauthError("이메일 동의가 필요합니다. 카카오 개발자 콘솔 [동의항목]에서 이메일을 설정하세요.");
            }
            String email = emailObj.toString();

            String nickname = resolveNickname(kakaoAccount);

            OAuthDTO dto = new KakaoDTO(socialUid, email, nickname);

            Users users = usersRepository.findBySocialTypeAndSocialUid(providerId, socialUid)
                    .orElseGet(() -> {
                        Users newUsers = UsersConverter.toUser(dto);
                        return usersRepository.save(newUsers);
                    });

            return new OAuthUsers(users, oAuth2User.getAttributes());
        } catch (OAuth2AuthenticationException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw oauthError("지원하지 않는 소셜 로그인입니다.");
        } catch (Exception e) {
            throw oauthError("카카오 로그인 처리 중 오류: " + e.getMessage());
        }
    }

    private String resolveNickname(Map<String, Object> kakaoAccount) {
        Object profileObj = kakaoAccount.get("profile");
        if (profileObj instanceof Map<?, ?> profile) {
            Object nickname = profile.get("nickname");
            if (nickname != null) {
                return nickname.toString();
            }
        }
        return "카카오유저";
    }

    private OAuth2AuthenticationException oauthError(String message) {
        return new OAuth2AuthenticationException(new OAuth2Error("oauth_error", message, null));
    }
}
