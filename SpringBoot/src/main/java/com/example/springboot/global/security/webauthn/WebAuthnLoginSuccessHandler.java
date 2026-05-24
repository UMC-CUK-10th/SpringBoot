package com.example.springboot.global.security.webauthn;

import com.example.springboot.domain.users.entity.Users;
import com.example.springboot.domain.users.exception.UsersErrorCode;
import com.example.springboot.domain.users.exception.UsersException;
import com.example.springboot.domain.users.repository.UsersRepository;
import com.example.springboot.global.security.entity.AuthUsers;
import com.example.springboot.global.security.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class WebAuthnLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Users users = usersRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsersException(UsersErrorCode.USERS_NOT_FOUND));

        String accessToken = jwtUtil.createAccessToken(new AuthUsers(users));
        String body = """
                {"success":true,"authenticated":true,"redirectUrl":"/passkey-test.html","userId":%d,"email":"%s","accessToken":"%s"}
                """.formatted(users.getId(), escapeJson(users.getEmail()), accessToken);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(body);
    }

    private String escapeJson(String value) {
        return value == null ? "" : value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
