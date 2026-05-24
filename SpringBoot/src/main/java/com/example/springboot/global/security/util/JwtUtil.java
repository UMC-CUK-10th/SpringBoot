package com.example.springboot.global.security.util;

import com.example.springboot.domain.users.entity.enums.SocialType;
import com.example.springboot.global.security.entity.AuthUsers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ) {
        this.secretKey = createSecretKey(secret);
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    /** HMAC-SHA256은 최소 256비트(32바이트) 키가 필요합니다. */
    private static SecretKey createSecretKey(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            try {
                keyBytes = MessageDigest.getInstance("SHA-256").digest(keyBytes);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("JWT secret key 생성에 실패했습니다.", e);
            }
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // AccessToken 생성
    public String createAccessToken(AuthUsers authUsers) {
        return createToken(authUsers, accessExpiration);
    }

    /** 토큰에서 이메일 가져오기
     *
     * @param token 유저 정보를 추출할 토큰
     * @return 유저 이메일을 토큰에서 추출합니다
     */
    public String getEmail(String token) {
        try {
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    /** 토큰에서 소셜 로그인 타입 가져오기
     *
     * @param token 유저 정보를 추출할 토큰
     * @return 유저 소셜 로그인 타입을 추출합니다
     */
    public SocialType getSocialType(String token) {
        try {
            Object socialType = getClaims(token).getPayload().get("social_type");
            if (socialType == null) {
                return null;
            }
            return SocialType.valueOf(socialType.toString().toUpperCase());
        } catch (JwtException e) {
            return null;
        }
    }

    /** 토큰 유효성 확인
     *
     * @param token 유효한지 확인할 토큰
     * @return True, False 반환합니다
     */
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 토큰 생성
    private String createToken(AuthUsers authUsers, Duration expiration) {
        Instant now = Instant.now();

        // 인가 정보
        String authorities = authUsers.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(authUsers.getUsername()) // User UID를 Subject로
                .claim("role", authorities)
                .claim("social_type", authUsers.getUsers().getSocialType())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiration)))
                .signWith(secretKey)
                .compact();
    }

    // 토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }
}
