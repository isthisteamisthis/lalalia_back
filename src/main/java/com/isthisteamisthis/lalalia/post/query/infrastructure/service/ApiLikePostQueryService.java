package com.isthisteamisthis.lalalia.post.query.infrastructure.service;

import com.isthisteamisthis.lalalia.like.command.domain.aggregate.entity.Like;
import com.isthisteamisthis.lalalia.like.command.domain.aggregate.vo.LikeVO;
import com.isthisteamisthis.lalalia.like.query.domain.repository.LikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiLikePostQueryService {

    private final LikeQueryRepository likeQueryRepository;

    @Transactional
    public boolean getLike(Long userNo, Long postNo) {
        // postNo 와 userNo 로 likeVO 생성
        LikeVO likeVO = new LikeVO(postNo, userNo);
        // likeVO 로 like 조회 : 사용자가 이 게시물에서 좋아요한적이 있는지
        Optional<Like> optionalLike = likeQueryRepository.findByLikeVO(likeVO);

        return optionalLike.isPresent();

    }
}
