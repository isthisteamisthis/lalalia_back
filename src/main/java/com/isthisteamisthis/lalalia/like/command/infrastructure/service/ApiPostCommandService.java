package com.isthisteamisthis.lalalia.like.command.infrastructure.service;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.repository.PostCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApiPostCommandService {

    private final PostCommandRepository postCommandRepository;

    // postNo으로 post 조회
    @Transactional
    public Post getPostByPostNo(Long postNo) {

        return postCommandRepository.findById(postNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post No"));
    }

}
