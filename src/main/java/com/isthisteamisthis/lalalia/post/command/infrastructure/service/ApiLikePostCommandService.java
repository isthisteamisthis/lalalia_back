package com.isthisteamisthis.lalalia.post.command.infrastructure.service;

import com.isthisteamisthis.lalalia.like.command.domain.repository.LikeCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApiLikePostCommandService {

    private final LikeCommandRepository likeCommandRepository;

    // 삭제하는 게시물과 관련된 like 전부 삭제하고 삭제된 행의 수 반환
    @Transactional
    public Integer deleteLikeWithPostNo(Long postNo) {
        return likeCommandRepository.deleteLikesByPostNo(postNo);
    }

}
