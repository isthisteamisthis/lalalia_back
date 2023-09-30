package com.isthisteamisthis.lalalia.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${spring.jwt.secret}")
    private String jwtSecret;

    @Value("${spring.jwt.token-validity-in-seconds}")
    private long jwtValidity;

    // 토큰 생성
    public String generateToken(String userId) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtValidity * 1000);

        return Jwts.builder()
                .setSubject(userId)     // userId 설정
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // 토큰에서 userId 추출
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    // 토큰의 유효성 검증
    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            // 토큰이 유효하지 않을 경우 예외 발생
            return false;
        }
    }

    // 토큰의 만료 여부 확인
    public boolean isTokenExpired(String jwtToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwtToken)
                    .getBody();

            Date expirationDate = claims.getExpiration();
            Date currentDate = new Date();

            // 만료일과 현재 날짜를 비교하여 토큰이 만료되었는지 확인
            return expirationDate.before(currentDate);
        } catch (Exception e) {
            // 토큰 파싱 또는 서명 검증 실패 시도 예외 발생
            return true; // 유효하지 않은 토큰으로 처리
        }
    }
}
