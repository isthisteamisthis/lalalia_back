package com.isthisteamisthis.lalalia.user.query.application.service;

import com.isthisteamisthis.lalalia.common.jwt.JwtTokenProvider;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.*;
import com.isthisteamisthis.lalalia.user.query.domain.repository.UserQueryRepository;
import com.isthisteamisthis.lalalia.user.query.infrastructure.service.ApiComposeSongUserQueryService;
import com.isthisteamisthis.lalalia.user.query.infrastructure.service.ApiPerfectScoreUserQueryService;
import com.isthisteamisthis.lalalia.user.query.infrastructure.service.ApiPostUserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserQueryRepository userQueryRepository;
    private final ApiPostUserQueryService apiPostUserQueryService;
    private final ApiPerfectScoreUserQueryService apiPerfectScoreUserQueryService;
    private final ApiComposeSongUserQueryService apiComposeSongUserQueryService;

    // userId 로 User 정보와 작성한 게시글,
    @Transactional(readOnly = true)
    public MyPageResponse findUserByUserId(Long userId) {

        User user = userQueryRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserId"));

        // 사용자가 작성한 게시물 리스트 가져오기
        List<Post> postList = apiPostUserQueryService.getMyPostList(user.getUserNo());
        List<PostResponse> postResponse = postList.stream().map(PostResponse::from).collect(Collectors.toList());
        // 사용자의 노래 리스트 가져오기
        List<PerfectScore> perfectScoreList = apiPerfectScoreUserQueryService.getMyPerfectScoreList(user.getUserNo());
        List<PerfectScoreResponse> perfectScoreResponse = perfectScoreList.stream().map(PerfectScoreResponse::from).collect(Collectors.toList());
        // 사용자의 ai 데모곡 리스트 가져오기
        List<ComposeSong> composeSongList = apiComposeSongUserQueryService.getMyComposeSongList(user.getUserNo());
        List<ComposeSongResponse> composeSongResponse = composeSongList.stream().map(ComposeSongResponse::from).collect(Collectors.toList());

        return MyPageResponse.from(user, postResponse, perfectScoreResponse, composeSongResponse);

    }

    // userNo로 User 가져오기
    @Transactional(readOnly = true)
    public UserResponse findUserByUserNo(Long userNo) {

        User user = userQueryRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserId"));

        return UserResponse.from(user);

    }

    // 헤더의 토큰으로 userId 가져오기
    @Transactional(readOnly = true)
    public Long getUserFromToken(String authorizationHeader) {
        // 헤더에서 토큰 추출
        String jwtToken = extractTokenFromHeader(authorizationHeader);
        // 토큰으로 userId 가져오기
        return jwtTokenProvider.getUserIdFromToken(jwtToken);

    }

    // 헤더에서 토큰 추출
    private String extractTokenFromHeader(String authorizationHeader) {
        // 헤더의 'Bearer 를 제거 -> 토큰 추출
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        throw new IllegalArgumentException("Invalid Authorization header");
    }

}
