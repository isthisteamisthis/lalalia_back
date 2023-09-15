package com.isthisteamisthis.lalalia.post.command.application.service;

import com.isthisteamisthis.lalalia.post.command.application.dto.request.CreatePostRequest;
import com.isthisteamisthis.lalalia.post.command.application.dto.response.CreatePostResponse;
import com.isthisteamisthis.lalalia.post.command.application.dto.response.DeletePostResponse;
import com.isthisteamisthis.lalalia.post.command.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.ComposeSongVO;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.PerfectScoreVO;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.post.command.domain.repository.PostCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostCommandRepository postCommandRepository;

    // 게시물 생성
    @Transactional
    public CreatePostResponse createPost(UserResponse userResponse, CreatePostRequest createPostRequest) {

        Post post = Post.builder()
                .likeCnt(0)
                .date(new Date())
                .composeSongVO(new ComposeSongVO(createPostRequest.getComposeSongNo()))
                .userNoVO(new UserNoVO(userResponse.getUserNo()))
                .content(null)
                .title(createPostRequest.getTitle())
                .perfectScoreVO(new PerfectScoreVO(createPostRequest.getPerfectScoreNo()))
                .build();

        Post savedPost = postCommandRepository.save(post);

        return CreatePostResponse.form(savedPost);
    }

    // 게시물 삭제
    @Transactional
    public DeletePostResponse deletePost(UserResponse user, Long postId) {

        Post findPost = postCommandRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid PostId"));

        if (findPost.getUserNoVO().getUserNo().equals(user.getUserNo())) {
            postCommandRepository.delete(findPost);

            return DeletePostResponse.from(postId);
        }

        throw new IllegalArgumentException("Not Matched Writer!!");
    }
}
