package com.example.springboot10th.global.security;

import com.example.springboot10th.domain.auth.dto.KakaoDTO;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.entity.UserSocial;
import com.example.springboot10th.domain.user.repository.UserRepository;
import com.example.springboot10th.domain.user.repository.UserSocialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;










@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserSocialRepository userSocialRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        return processOAuthUser(userRequest, oAuth2User);
    }

    


    @Transactional
    public OAuth2User processOAuthUser(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String provider = userRequest.getClientRegistration().getRegistrationId();

        KakaoDTO kakaoDTO = KakaoDTO.from(attributes);
        String providerId = String.valueOf(kakaoDTO.getId());

        log.info("OAuth2 로그인 시도 - provider: {}, providerId: {}", provider, providerId);

        Optional<UserSocial> existingSocial = userSocialRepository.findByProviderAndProviderId(provider, providerId);

        User user;
        boolean isNewUser;

        if (existingSocial.isPresent()) {
            user = existingSocial.get().getUser();
            isNewUser = false;
            log.info("기존 OAuth 사용자 로그인 - userId: {}", user.getId());
        } else {
            String email = kakaoDTO.getEmail();
            String nickname = kakaoDTO.getNickname() != null ? kakaoDTO.getNickname() : "카카오유저";

            user = userRepository.findByEmail(email).orElseGet(() -> {
                User newUser = User.builder()
                        .name(nickname)
                        .nickname(nickname)
                        .email(email != null ? email : provider + "_" + providerId + "@oauth.com")
                        .password("")
                        .phoneNum("")
                        .build();
                return userRepository.save(newUser);
            });

            UserSocial userSocial = UserSocial.builder()
                    .user(user)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userSocialRepository.save(userSocial);

            isNewUser = true;
            log.info("신규 OAuth 사용자 등록 - userId: {}, provider: {}", user.getId(), provider);
        }

        return new OAuthMember(user, isNewUser, attributes);
    }
}
