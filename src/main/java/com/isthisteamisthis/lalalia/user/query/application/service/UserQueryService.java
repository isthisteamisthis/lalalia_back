package com.isthisteamisthis.lalalia.user.query.application.service;

import com.isthisteamisthis.lalalia.common.jwt.JwtTokenProvider;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.MyPageResponse;
import com.isthisteamisthis.lalalia.user.query.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserQueryRepository userQueryRepository;

    // userId 로 User 찾기
    @Transactional
    public MyPageResponse findUserByUserId(Long userId) {

        User user = userQueryRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserId"));
        System.out.println("user = " + user.getEmail());

        return MyPageResponse.from(user);

    }

    // 헤더의 토큰으로 userId 가져오기
    @Transactional
    public Long getUserFromToken(String authorizationHeader) {

        String jwtToken = extractTokenFromHeader(authorizationHeader);

        return jwtTokenProvider.getUserIdFromToken(jwtToken);

    }

    private String extractTokenFromHeader(String authorizationHeader) {

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        throw new IllegalArgumentException("Invalid Authorization header");
    }

}
