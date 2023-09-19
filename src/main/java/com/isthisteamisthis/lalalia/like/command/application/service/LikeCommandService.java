package com.isthisteamisthis.lalalia.like.command.application.service;

import com.isthisteamisthis.lalalia.like.command.application.dto.response.CreateLikeResponse;
import com.isthisteamisthis.lalalia.like.command.application.dto.response.DeleteLikeResponse;
import com.isthisteamisthis.lalalia.like.command.domain.aggregate.entity.Like;
import com.isthisteamisthis.lalalia.like.command.domain.aggregate.vo.LikeVO;
import com.isthisteamisthis.lalalia.like.command.domain.repository.LikeCommandRepository;
import com.isthisteamisthis.lalalia.like.command.infrastructure.service.ApiPostCommandService;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeCommandService {

    private final LikeCommandRepository likeCommandRepository;
    private final ApiPostCommandService apiPostCommandService;

    // 좋아요 생성
    @Transactional
    public CreateLikeResponse createLikeWithPostNoAndUser(Long postNo, UserResponse user) {
        // postNo로 게시물 찾기
        Post findPost = apiPostCommandService.getPostByPostNo(postNo);
        // 게시물이 존재한다면 사용자와 postNo로 likeVO 생성
        LikeVO likeVO = new LikeVO(findPost.getPostNo(), user.getUserNo());
        // likeVO로 Like 가 존재하는지 확인
        Optional<Like> optionalLike = likeCommandRepository.findByLikeVO(likeVO);
        // 이미 존재한다면 예외 처리
        if (optionalLike.isPresent()) {
            throw new IllegalArgumentException("This Like is Already exist!!");
        }
        // 존재하지않는다면 like 생성해서 저장
        Like like = Like.builder()
                .likeVO(likeVO)
                .build();

        likeCommandRepository.save(like);
        // post 의 좋아요 수 증가
        findPost.updateLikeCnt(findPost.getLikeCnt() + 1);

        return CreateLikeResponse.from(likeVO);

    }

    // 좋아요 삭제
    @Transactional
    public DeleteLikeResponse deleteLikeWithPostNoAndUser(Long postNo, UserResponse user) {
        // postNo로 게시물 찾기
        Post findPost = apiPostCommandService.getPostByPostNo(postNo);
        // 게시물이 존재한다면 사용자와 postNo로 likeVO 생성
        LikeVO likeVO = new LikeVO(findPost.getPostNo(), user.getUserNo());
        // likeVO로 like 가 존재하는지 확인
        Optional<Like> optionalLike = likeCommandRepository.findByLikeVO(likeVO);
        // 존재하지않는다면 예외처리
        if (optionalLike.isEmpty()) {
            throw new IllegalArgumentException("This Like is doesn't exist!!");
        }
        // 존재한다면 like 생성해서 삭제
        Like like = Like.builder()
                .likeVO(likeVO)
                .build();

        likeCommandRepository.delete(like);
        // post 의 좋아요 수 감소
        findPost.updateLikeCnt(findPost.getLikeCnt() - 1);

        return DeleteLikeResponse.from(likeVO);

    }
}
