package com.isthisteamisthis.lalalia.user.query.application.service;

import com.isthisteamisthis.lalalia.common.jwt.JwtTokenProvider;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.MyPageResponse;
import com.isthisteamisthis.lalalia.user.query.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserQueryRepository userQueryRepository;

    // userId 로 User 찾기
    @Transactional
    public MyPageResponse findUserByUserId(Long userId) {

        Optional<User> optionalUser = userQueryRepository.findByUserId(userId);

        if (optionalUser.isPresent()) {
           User user = optionalUser.get();

           return new MyPageResponse(
                   user.getUserNo(),
                   user.getUserId(),
                   user.getNickname(),
                   user.getEmail(),
                   user.getCategory(),
                   user.getUserIntro(),
                   user.getAvgScore(),
                   user.getMaxRange().getMaxFrequency(),
                   user.getMaxRange().getMaxNote(),
                   user.getMaxRange().getMaxOctave(),
                   user.getMinRange().getMinFrequency(),
                   user.getMinRange().getMinNote(),
                   user.getMinRange().getMinOctave()
           );
        }

        throw new IllegalArgumentException("Inavlid UserId");
    }

    // 헤더의 토큰으로 userId 가져오기
    @Transactional
    public Long getUserFromToken(String authoriztionHeader) {

        String jwtToken = extractTokenFromHeader(authoriztionHeader);

        return jwtTokenProvider.getUserIdFromToken(jwtToken);

    }

    private String extractTokenFromHeader(String authoriztionHeader) {

        if (authoriztionHeader != null && authoriztionHeader.startsWith("Bearer ")) {
            return authoriztionHeader.substring(7);
        }

        throw new IllegalArgumentException("Invalid Authorization header");
    }

}
