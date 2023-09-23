package com.isthisteamisthis.lalalia.user.query.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.MyPageResponse;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.user.query.application.service.UserQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "회원 Query API")
@RestController
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryService userQueryService;

    // My Page 조회
    @GetMapping("/my-page")
    public ResponseEntity<ApiResponse> getMyPage(@RequestHeader Map<String, String> requestHeader) {
        // 헤더에 있는 jwtToken 추출
        String authorizationHeader = requestHeader.get("authorization");
        // jwtToken 에서 userId 추출
        Long userId = userQueryService.getUserFromToken(authorizationHeader);
        // userId 로 사용자 정보와 곡 리스트들 조회
        MyPageResponse myPageResponse = userQueryService.findUserByUserId(userId);

        return ResponseEntity.ok(ApiResponse.success("My Page 조회 성공", myPageResponse));
    }

    @GetMapping("/my-page/{userNo}")
    public ResponseEntity<ApiResponse> getMyPage(@RequestHeader Map<String, String> requestHeader, @PathVariable("userNo") Long userNo) {
        // 헤더에 있는 jwtToken 추출
        String authorizationHeader = requestHeader.get("authorization");
        // jwtToken 에서 userId 추출
        Long userId = userQueryService.getUserFromToken(authorizationHeader);
        // userId 로 사용자 정보와 곡 리스트들 조회
        UserResponse userResponse = userQueryService.findUserByUserNo(userNo);

        return ResponseEntity.ok(ApiResponse.success("My Page 조회 성공", userResponse));
    }

}
