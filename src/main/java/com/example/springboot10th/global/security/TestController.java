package com.example.springboot10th.global.security;

import com.example.springboot10th.domain.user.repository.UserRepository;
import com.example.springboot10th.domain.user.repository.UserSocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;
    private final UserSocialRepository userSocialRepository;

    @GetMapping("/api/v1/auth/test-db")
    public Map<String, Object> testDb() {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("userCount", userRepository.count());
            map.put("userSocialCount", userSocialRepository.count());
            map.put("status", "OK");
        } catch (Exception e) {
            map.put("status", "ERROR");
            map.put("error", e.getMessage());
            map.put("class", e.getClass().getName());
        }
        return map;
    }

    @GetMapping("/api/v1/auth/db-data")
    public Map<String, Object> dbData() {
        Map<String, Object> map = new HashMap<>();
        try {
            java.util.List<Map<String, Object>> users = userRepository.findAll().stream().map(u -> {
                Map<String, Object> m = new HashMap<>();
                m.put("id", u.getId());
                m.put("name", u.getName());
                m.put("nickname", u.getNickname());
                m.put("email", u.getEmail());
                m.put("password", u.getPassword());
                m.put("phoneNum", u.getPhoneNum());
                return m;
            }).collect(java.util.stream.Collectors.toList());

            java.util.List<Map<String, Object>> socials = userSocialRepository.findAll().stream().map(s -> {
                Map<String, Object> m = new HashMap<>();
                m.put("id", s.getId());
                m.put("provider", s.getProvider());
                m.put("providerId", s.getProviderId());
                m.put("userId", s.getUser() != null ? s.getUser().getId() : null);
                return m;
            }).collect(java.util.stream.Collectors.toList());

            map.put("users", users);
            map.put("userSocials", socials);
            map.put("status", "OK");
        } catch (Exception e) {
            map.put("status", "ERROR");
            map.put("error", e.getMessage());
        }
        return map;
    }
}
