package com.isthisteamisthis.umchiumtee.user.command.application.controller;

import com.isthisteamisthis.umchiumtee.user.command.application.dto.response.KakaoProfileResponse;
import com.isthisteamisthis.umchiumtee.user.command.application.service.KakaoAuthService;
import com.isthisteamisthis.umchiumtee.user.command.application.service.UserCommandService;
import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "회원 Command API")
@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;
    private final KakaoAuthService kakaoAuthService;

    // 카카오로 로그인
    @PostMapping("/login-kakao")
    public ResponseEntity<String> loginWithKakao(@RequestBody Map<String, String> requestBody) {

        String accessToken = requestBody.get("accessToken");

        if (accessToken != null) {
            // 액세스 토큰으로 카카오에서 해당 유저 정보 가져오기
            KakaoProfileResponse kakaoProfileResponse = kakaoAuthService.getKakaoProfile(accessToken);

            // 유저의 Id로 회원으로 등록이 되어있는지 확인
            User findUser = kakaoAuthService.findByUserId(kakaoProfileResponse.getUserId());

            // 회원이 등록되어있다면 토큰 생성해서 반환
            String jwtToken;

            if (findUser != null) {
                jwtToken = userCommandService.generateToken(findUser);
            }
            // 회원이 등록되어있지않다면 회원가입하고 토큰 생성해서 반환
            else {
                jwtToken = userCommandService.signup(kakaoProfileResponse);
            }

            // Jwt Token 을 Header 에 담아서 반환
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwtToken);

            return new ResponseEntity<>(headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 앱 진입 시 jwt Token 유효성 검사
    @PostMapping("/login")
    public ResponseEntity<?> loginInApp(@RequestHeader Map<String, String> requestHeader) {

        String jwtToken = requestHeader.get("authorization");

        boolean isValidToken = userCommandService.checkToken(jwtToken);

        if (isValidToken) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
