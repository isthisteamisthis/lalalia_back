package com.isthisteamisthis.lalalia.user.command.application.service;

import com.isthisteamisthis.lalalia.user.command.application.dto.response.KakaoProfileResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.KakaoTokenResponse;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    private final RestTemplate restTemplate;
    private final UserCommandRepository userCommandRepository;

    @Transactional
    public String loginWithKakao(String code) {
        // 카카오로부터 받은 인가 코드로 토큰 요청
        String tokenUrl = "https://kauth.kakao.com/oauth/token?" +
                "grant_type=authorization_code" +
                "&client_id=" + kakaoClientId +
                "&client_secret=" + kakaoClientSecret +
                "&redirect_uri=" + kakaoRedirectUri +
                "&code=" + code;

        // access token 요청
        ResponseEntity<KakaoTokenResponse> response = new RestTemplate().exchange(
                tokenUrl,
                HttpMethod.POST,
                null,
                KakaoTokenResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            KakaoTokenResponse kakaoTokenResponse = response.getBody();

            if (kakaoTokenResponse != null) {
                return kakaoTokenResponse.getAccessToken();
            }
        }
        return null;

    }

    // 액세스 토큰으로 user profile 가져오기
    @Transactional
    public KakaoProfileResponse getKakaoProfile(String kakaoAccessToken){
        // user 정보를 가져오는 kakao api url
        String url = "https://kapi.kakao.com/v2/user/me";

        // http header 설정 : access token 을 넣어서 user 정보에 접근할 수 있도록 한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + kakaoAccessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 필요한 사용자 정보 가져오기
        MultiValueMapAdapter<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"id\", \"kakao_account.email\", \"properties.nickname\"]");

        HttpEntity<?> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(url, request, KakaoProfileResponse.class);

    }

    // 특정 시용자 조회
    @Transactional
    public User findByUserId(Long userId) {

        Optional<User> optionalUser = userCommandRepository.findByUserId(userId);

        return optionalUser.orElse(null);

    }

}
