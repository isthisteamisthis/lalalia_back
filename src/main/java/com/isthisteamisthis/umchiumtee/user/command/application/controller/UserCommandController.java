package com.isthisteamisthis.umchiumtee.user.command.application.controller;

import com.isthisteamisthis.umchiumtee.user.command.application.dto.response.KakaoProfileResponse;
import com.isthisteamisthis.umchiumtee.user.command.application.service.KakaoAuthService;
import com.isthisteamisthis.umchiumtee.user.command.application.service.UserCommandService;
import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Tag(name = "회원 Command API")
@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;
    private final KakaoAuthService kakaoAuthService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String authorizatinoHeader) {
        try {
            boolean tokenValidation = userCommandService.checkToken(authorizatinoHeader);

            if (tokenValidation) {
                // 로그인 로직
                return ResponseEntity.ok("Login Success");

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .header("Location", "/oauth")
                        .build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR");
        }
    }

    //@RequestParam 은 파라미터의 타입이 기본 타입이거나 쿼리 스트링의 기본 타입인 String 인 경우 어노테이션 생략 가능하다.
    @GetMapping("/oauth")
    public ResponseEntity<Object> signInWithKakao(@RequestParam String code) {

        // 카카오로부터 받은 인가 코드를 처리하여 액세스 토큰을 생성
        String accessToken = kakaoAuthService.loginWithKakao(code);

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

            return ResponseEntity.ok(jwtToken);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
