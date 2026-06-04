package com.example.springboot10th.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;














@Getter
@NoArgsConstructor
public class KakaoDTO {

    private Long id;                       
    private String email;
    private String nickname;

    


    @SuppressWarnings("unchecked")
    public static KakaoDTO from(Map<String, Object> attributes) {
        KakaoDTO dto = new KakaoDTO();
        dto.id = Long.parseLong(String.valueOf(attributes.get("id")));

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        if (kakaoAccount != null) {
            dto.email = (String) kakaoAccount.get("email");

            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            if (profile != null) {
                dto.nickname = (String) profile.get("nickname");
            }
        }
        return dto;
    }
}
