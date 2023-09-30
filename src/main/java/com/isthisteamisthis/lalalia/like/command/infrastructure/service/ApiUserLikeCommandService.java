package com.isthisteamisthis.lalalia.like.command.infrastructure.service;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.user.query.application.service.UserQueryService;
import com.isthisteamisthis.lalalia.user.query.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiUserLikeCommandService {

    private final UserQueryService userQueryService;
    private final UserQueryRepository userQueryRepository;

    // jwt 토큰으로 사용자 조회
    public UserResponse getUser(String authorizationHeader) {

        // 토큰에서 userId 추출
        Long userId = userQueryService.getUserFromToken(authorizationHeader);
        // userId로 사용자 조회
        User user = userQueryRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserId"));

        return UserResponse.from(user);
    }

}