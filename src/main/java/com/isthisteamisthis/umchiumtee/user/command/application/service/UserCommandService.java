package com.isthisteamisthis.umchiumtee.user.command.application.service;

import com.isthisteamisthis.umchiumtee.common.jwt.JwtTokenProvider;
import com.isthisteamisthis.umchiumtee.user.command.application.dto.request.CreateUserRequest;
import com.isthisteamisthis.umchiumtee.user.command.application.dto.response.KakaoProfileResponse;
import com.isthisteamisthis.umchiumtee.user.command.application.dto.response.UserCommandResponse;
import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.umchiumtee.user.command.domain.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserCommandService {

    private final UserCommandRepository userCommandRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserCommandResponse createNewUser(CreateUserRequest userDTO) {
        //음역대 측정 메소드 결과물 -> 빌더 사용 시 넣어주기

        User user = userCommandRepository.save(User.builder().build());  // 카카오 로그인 시 넘어오는 정보 넣기

        return UserCommandResponse.from(user);

    }

    // 회원 가입
    @Transactional
    public String signup(KakaoProfileResponse kakaoProfileResponse) {
        // 회원가입 로직 :  사용자 정보를 저장하고, 토큰을 생성하여 반환
        User user = User.builder()
                .userId(kakaoProfileResponse.getUserId())
                .nickname(kakaoProfileResponse.getNickname())
                .email(kakaoProfileResponse.getEmail())
                .build();

        userCommandRepository.save(user);

        // 토큰 생성 로직
        return generateToken(user);

    }

    // JWT 토큰 생성
    @Transactional
    public String generateToken(User user) {
        // 토큰 생성 로직 : 사용자 정보를 토큰에 포함하거나, 특정 시간에 대한 만료 시간을 설정
        return jwtTokenProvider.generateToken(String.valueOf(user.getUserId()));

    }

    // JWT 토큰 유효성 확인
    @Transactional
    public boolean checkToken(String authorizationHeader) {

        String jwtToken = extractTokenFromHeader(authorizationHeader);

        return !jwtTokenProvider.isTokenExpired(jwtToken) && jwtTokenProvider.validateToken(jwtToken);

    }

    // 헤더의 토큰으로 userId 가져오기
    public long getUserFromToken(String authorizationHeader) {

        String jwtToken = extractTokenFromHeader(authorizationHeader);

        return jwtTokenProvider.getUserIdFromToken(jwtToken);

    }

    // JWT 토큰 추출 로직
    private String extractTokenFromHeader(String authorizationHeader) {

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        throw new IllegalArgumentException("Invalid Authorization header");

    }

}