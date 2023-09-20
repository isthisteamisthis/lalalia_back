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
import com.isthisteamisthis.lalalia.post.command.infrastructure.service.ApiLikePostCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostCommandRepository postCommandRepository;
    private final ApiLikePostCommandService apiLikePostCommandService;

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
    public DeletePostResponse deletePost(UserResponse user, Long postNo) {
        // post Id로 게시물 조회
        Post findPost = postCommandRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid PostNo"));
        // 게시물의 작성자와 요청하는 사용자가 같은지 확인 -> 다르면 삭제 불가능
        if (findPost.getUserNoVO().getUserNo().equals(user.getUserNo())) {
            postCommandRepository.delete(findPost);
            // 연관된 좋아요 삭제 : 삭제된 행의 수
            Integer deleteLikeCnt = apiLikePostCommandService.deleteLikeWithPostNo(postNo);
            // 삭제한 게시물의 postNo 반환
            return DeletePostResponse.from(postNo, deleteLikeCnt);
        }

        throw new IllegalArgumentException("Not Matched Writer!!");
    }
}
