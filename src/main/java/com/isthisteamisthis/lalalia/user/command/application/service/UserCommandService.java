package com.isthisteamisthis.lalalia.user.command.application.service;

import com.isthisteamisthis.lalalia.common.jwt.JwtTokenProvider;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MaxRangeVO;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
import com.isthisteamisthis.lalalia.user.command.domain.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserCommandRepository userCommandRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String SPLIT_CHAR = ",";

    // 회원 가입
    @Transactional
    public String signup(com.isthisteamisthis.lalalia.user.command.application.dto.response.KakaoProfileResponse kakaoProfileResponse) {
        // 회원가입 로직 :  사용자 정보를 저장하고, 토큰을 생성하여 반환
        User user = User.builder()
                .userId(kakaoProfileResponse.getUserId())
                .nickname(kakaoProfileResponse.getNickname())
                .email(kakaoProfileResponse.getEmail())
                .category(null)
                .userIntro("당신을 소개해주세요.")
                .avgScore((float) 0)
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
    @Transactional
    public Long getUserIdFromToken(String authorizationHeader) {
        // 헤더로 넘어온 정보에서 jwt 토큰 부분 추출
        String jwtToken = extractTokenFromHeader(authorizationHeader);

        return jwtTokenProvider.getUserIdFromToken(jwtToken);

    }

    // 헤더의 토큰으로 user 가져오기
    @Transactional
    public User getUserFromToken(String authorizationHeader) {
        // 헤더로 넘어온 정보에서 jwt 토큰 부분 추출
        String jwtToken = extractTokenFromHeader(authorizationHeader);
        // 토큰에서 userId 추출
        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        // userId로 사용자 조회
        Optional<User> optionalUser = userCommandRepository.findByUserId(userId);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        throw new IllegalArgumentException("Invalid UserId");

    }

    // JWT 토큰 추출 로직
    private String extractTokenFromHeader(String authorizationHeader) {

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        throw new IllegalArgumentException("Invalid Authorization header");

    }

    // 작곡가, 가수 선택한 항목 저장
    @Transactional
    public void selectCategory(Long userId, String category) {
        // userId 로 user 가져오기
        User user = userCommandRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserId"));
        // user 에 category 추가
        user.addCategory(category);

    }

    @Transactional
    public void addMaxVoiceRange(User user, MaxVoiceRangeResponse response) {
        user.addMaxVoiceRange(new MaxRangeVO(Float.parseFloat(response.getHighestfrequency()), response.getNote(), response.getOctave()));
    }

    @Transactional
    public void addMinVoiceRange(User user, MinVoiceRangeResponse response) {
        user.addMinVoiceRange(new MinRangeVO(Float.parseFloat(response.getLowestfrequency()), response.getNote(), response.getOctave()));
    }


    @Transactional
    public void addRecommendSongList(User user, Map<String, String> songMap) {

        StringBuilder resultBuilder = new StringBuilder();

        songMap.keySet().forEach(value -> {
            if (resultBuilder.length() > 0) {
                resultBuilder.append(",");
            }
            resultBuilder.append(value);
        });

        String result = resultBuilder.toString();

        user.addRecommendSongList(result);
    }

    @Transactional
    public void updateAvgScore(Long userNo, Float score) {

        Optional<User> optionalUser = userCommandRepository.findById(userNo);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.updateAvgScore(score);
        }
    }
}
