package com.isthisteamisthis.lalalia.message.command.infrastructure.service;


import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.user.query.application.service.UserQueryService;
import com.isthisteamisthis.lalalia.user.query.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiUserMessageCommandService {

    private final UserQueryService userQueryService;
    private final UserQueryRepository userQueryRepository;

    public UserResponse getUser(String authorizationHeader) {

        Long userId = userQueryService.getUserFromToken(authorizationHeader);

        User user = userQueryRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserId"));

        return UserResponse.from(user);
    }

    public UserResponse getUserByUserNo(Long userNo) {

        User user = userQueryRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserNo"));

        return UserResponse.from(user);
    }

}
