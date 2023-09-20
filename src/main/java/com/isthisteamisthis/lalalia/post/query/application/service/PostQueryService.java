package com.isthisteamisthis.lalalia.post.query.application.service;

import com.isthisteamisthis.lalalia.post.command.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetAllPostsResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetMyPagePostResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetPostResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetUserPostResponse;
import com.isthisteamisthis.lalalia.post.query.domain.repository.PostQueryRepository;
import com.isthisteamisthis.lalalia.post.query.infrastructure.service.ApiLikePostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostQueryRepository postQueryRepository;
    private final ApiLikePostQueryService apiLikePostQueryService;

    // 사용자의 게시물 전체 조회
    @Transactional(readOnly = true)
    public GetUserPostResponse getPostsByUserNo(UserResponse user) {

        Long userNo = user.getUserNo();
        // userNoVO 로 사용자의 전체 게시물 조회
        List<Post> postList = postQueryRepository.findPostsByUserNoVO(new UserNoVO(userNo));

        return GetUserPostResponse.from(postList);

    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public GetAllPostsResponse getAllPosts() {
        // 게시물 전체 조회 : 좋아요 내림차순
        List<Post> postList = postQueryRepository.findAllByOrderByLikeCntDesc();

        return GetAllPostsResponse.from(postList);
    }

    // my page : 하나의 게시물 상세 조회
    @Transactional(readOnly = true)
    public GetMyPagePostResponse getMyPagePost(UserResponse user, Long postNo) {
        // post Id로 게시물 조회
        Post findPost = postQueryRepository.findByPostNo(postNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post Id"));
        // 작성자와 요청자가 같은지 확인 : 다르면 예외처리
        if (!((findPost.getUserNoVO().getUserNo()).equals(user.getUserNo()))) {
            throw new IllegalArgumentException("Not Matched Writer!!");
        }
        // 사용자가 게시물에 좋아요를 했는지 여부
        boolean like = apiLikePostQueryService.getLike(user.getUserNo(), postNo);

        return GetMyPagePostResponse.from(findPost, like);
    }

    // community : 하나의 게시물 상세 조회
    @Transactional(readOnly = true)
    public GetPostResponse getCommunityPost(UserResponse user, Long postNo) {
        // post Id로 게시물 조회
        Post findPost = postQueryRepository.findByPostNo(postNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post Id"));
        // 게시물 작성자가 본인인지 여부
        boolean isMe = (findPost.getUserNoVO().getUserNo()).equals(user.getUserNo());
        // 사용자가 게시물에 좋아요를 했는지 여부
        boolean like = apiLikePostQueryService.getLike(user.getUserNo(), postNo);

        return GetPostResponse.from(findPost, isMe, like);

    }
}
