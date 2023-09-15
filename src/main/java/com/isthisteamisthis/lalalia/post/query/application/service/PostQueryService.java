package com.isthisteamisthis.lalalia.post.query.application.service;

import com.isthisteamisthis.lalalia.post.command.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetAllPostsResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetUserPostResponse;
import com.isthisteamisthis.lalalia.post.query.domain.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostQueryRepository postQueryRepository;

    // 사용자의 게시물 전체 조회
    public GetUserPostResponse getPostsByUserNo(UserResponse user) {

        Long userNo = user.getUserNo();

        List<Post> postList = postQueryRepository.findPostsByUserNoVO(new UserNoVO(userNo));

        return GetUserPostResponse.from(postList);

    }

    // 게시글 전체 조회
    public GetAllPostsResponse getAllPosts() {

        List<Post> postList = postQueryRepository.findAll();

        return GetAllPostsResponse.from(postList);
    }
}
