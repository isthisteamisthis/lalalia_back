package com.isthisteamisthis.lalalia.user.query.infrastructure.service;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.post.query.application.service.PostQueryService;
import com.isthisteamisthis.lalalia.post.query.domain.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiPostUserQueryService {

    private final PostQueryRepository postQueryRepository;

    @Transactional
    public List<Post> getMyPostList(Long userNo) {

        // UserNo로 UserNoVO 생성
        UserNoVO userNoVO = new UserNoVO(userNo);
        // userNo로 연관된 Post 전부 조회해서 반환
        return postQueryRepository.findPostsByUserNoVO(userNoVO);

    }

}
