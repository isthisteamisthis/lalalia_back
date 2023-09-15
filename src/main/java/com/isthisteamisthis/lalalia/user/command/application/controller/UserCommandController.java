package com.isthisteamisthis.lalalia.user.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.user.command.application.dto.request.CategoryRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.request.VoiceRangeRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.CategoryResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.service.KakaoAuthService;
import com.isthisteamisthis.lalalia.user.command.application.service.UserCommandService;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.infrastructure.service.VoiceRangeInfraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Tag(name = "회원 Command API")
@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;
    private final VoiceRangeInfraService voiceRangeInfraService;
    private final SaveWAVFileService saveWAVFileService;

    private final KakaoAuthService kakaoAuthService;

    // 카카오로 로그인
    @PostMapping("/login-kakao")
    public ResponseEntity<?> loginWithKakao(@RequestBody Map<String, String> requestBody) {

        String accessToken = requestBody.get("accessToken");

        if (accessToken != null) {
            // 액세스 토큰으로 카카오에서 해당 유저 정보 가져오기
            com.isthisteamisthis.lalalia.user.command.application.dto.response.KakaoProfileResponse kakaoProfileResponse = kakaoAuthService.getKakaoProfile(accessToken);

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

        String authorizationHeader = requestHeader.get("authorization");

        boolean isValidToken = userCommandService.checkToken(authorizationHeader);

        if (isValidToken) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 작곡가, 가수 선택
    @PostMapping("/category")
    public ResponseEntity<ApiResponse> selectCategory(@RequestHeader Map<String, String> requestHeader, @RequestBody CategoryRequest categoryRequest) {
        // 헤더에서 jwt 토큰 추출
        String authorizationHeader = requestHeader.get("authorization");
        // jwt 토큰을 이용해서 userID 추출
        Long userId = userCommandService.getUserIdFromToken(authorizationHeader);
        // user 에 category 추가
        userCommandService.selectCategory(userId, categoryRequest.getCategory());

        CategoryResponse categoryResponse = new CategoryResponse(categoryRequest.getCategory());

        return ResponseEntity.ok(ApiResponse.success("category 등록 완료", categoryResponse));
    }

    @Operation(summary = "최고 음역대 생성")
    @PostMapping("/api/max-voice-range")
    public ResponseEntity<ApiResponse> createMaxVoiceRange(@RequestPart("voice-range") MultipartFile rangeWav) throws IOException {

        Long userNo = 1L;

        saveWAVFileService.saveAiSongFile(rangeWav);

        MaxVoiceRangeResponse maxResponse = voiceRangeInfraService.getMaxRange(userNo, rangeWav.getResource());

        userCommandService.addMaxVoiceRange(userNo, maxResponse);
        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", maxResponse));
    }

    @Operation(summary = "최저 음역대 생성")
    @PostMapping("/api/min-voice-range")
    public ResponseEntity<ApiResponse> createMinVoiceRange(VoiceRangeRequest request, @RequestPart("voice-range") MultipartFile rangeWav) throws IOException {

        Long userNo = 1L;

        saveWAVFileService.saveAiSongFile(rangeWav);

        MinVoiceRangeResponse minResponse = voiceRangeInfraService.getMinRange(userNo, rangeWav.getResource());

        userCommandService.addMinVoiceRange(userNo, minResponse);
        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", minResponse));
    }

}
