package com.isthisteamisthis.lalalia.user.query.infrastructure.repository;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiPostUserQueryRepository extends JpaRepository<Post, Long> {

    // 내 게시물 전체 조회
    List<Post> findPostsByUserNoVO(UserNoVO userNoVO);

}
