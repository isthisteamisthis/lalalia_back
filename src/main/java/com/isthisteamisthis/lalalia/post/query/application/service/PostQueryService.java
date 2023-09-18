package com.isthisteamisthis.lalalia.post.query.application.service;

import com.isthisteamisthis.lalalia.post.command.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetAllPostsResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetMyPagePostResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetPostResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetUserPostResponse;
import com.isthisteamisthis.lalalia.post.query.domain.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostQueryRepository postQueryRepository;

    // 사용자의 게시물 전체 조회
    @Transactional(readOnly = true)
    public GetUserPostResponse getPostsByUserNo(UserResponse user) {

        Long userNo = user.getUserNo();

        List<Post> postList = postQueryRepository.findPostsByUserNoVO(new UserNoVO(userNo));

        return GetUserPostResponse.from(postList);

    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public GetAllPostsResponse getAllPosts() {

        // TODO : 좋아요 순으로 정렬하는 로직 필요

        List<Post> postList = postQueryRepository.findAll();

        return GetAllPostsResponse.from(postList);
    }

    // my page : 하나의 게시물 상세 조회
    @Transactional(readOnly = true)
    public GetMyPagePostResponse getMyPagePost(UserResponse user, Long postId) {
        // post Id로 게시물 조회
        Post findPost = postQueryRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post Id"));
        // 작성자와 요청자가 같은지 확인 : 다르면 예외처리
        if (!findPost.getUserNoVO().getUserNo().equals(user.getUserNo())) {
            throw new IllegalArgumentException("Not Matched Writer!!");
        }

        // TODO : 좋아요 확인하는 로직 필요 (내가 좋아요를 한 게시물인지)

        return GetMyPagePostResponse.from(findPost);
    }

    // community : 하나의 게시물 상세 조회
    @Transactional(readOnly = true)
    public GetPostResponse getCommunityPost(UserResponse user, Long postId) {
        // post Id로 게시물 조회
        Post findPost = postQueryRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post Id"));
        // 게시물 작성자가 본인인지 여부
        boolean isMe = findPost.getUserNoVO().getUserNo().equals(user.getUserNo());

        // TODO : 좋아요 확인하는 로직 필요 (내가 좋아요를 한 게시물인지)

        return GetPostResponse.from(findPost, isMe);

    }
}
