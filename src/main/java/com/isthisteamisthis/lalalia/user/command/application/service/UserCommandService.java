package com.isthisteamisthis.lalalia.user.command.application.service;

import com.isthisteamisthis.lalalia.common.jwt.JwtTokenProvider;
import com.isthisteamisthis.lalalia.user.command.application.dto.request.CreateUserRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.UserCommandResponse;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MaxRangeVO;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
import com.isthisteamisthis.lalalia.user.command.domain.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserCommandService {

    private final UserCommandRepository userCommandRepository;

    private final JwtTokenProvider jwtTokenProvider;

    // 회원 가입
    @Transactional
    public String signup(com.isthisteamisthis.lalalia.user.command.application.dto.response.KakaoProfileResponse kakaoProfileResponse) {
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

        // 헤더로 넘어온 정보에서 jwt 토큰 부분 추출
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

    @Transactional
    public UserCommandResponse createNewUser(CreateUserRequest userDTO) {

        User user = userCommandRepository.save(User.builder().build());

        return UserCommandResponse.from(user);
    }

    @Transactional
    public void addMaxVoiceRange(Long id, MaxVoiceRangeResponse response) {

        User user = userCommandRepository.findById(1L).orElseThrow(
                NoSuchElementException::new);

        user.addMaxVoiceRange(new MaxRangeVO(Float.parseFloat(response.getHighestfrequency()), response.getNote(), response.getOctave()));
    }

    @Transactional
    public void addMinVoiceRange(Long id, MinVoiceRangeResponse response) {

        User user = userCommandRepository.findById(1L).orElseThrow(
                NoSuchElementException::new);

        user.addMinVoiceRange(new MinRangeVO(Float.parseFloat(response.getLowestfrequency()), response.getNote(), response.getOctave()));
    }


}
