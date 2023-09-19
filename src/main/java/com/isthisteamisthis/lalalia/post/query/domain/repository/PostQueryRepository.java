package com.isthisteamisthis.lalalia.post.query.domain.repository;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostQueryRepository extends JpaRepository<Post, Long> {

    // 좋아요 내림차순으로 게시물 전체 조회
    List<Post> findAllByOrderByLikeCntDesc();

    // 사용자의 게시물 전체 조회
    List<Post> findPostsByUserNoVO(UserNoVO userNoVO);

    // postNo 로 게시글 조회
    Optional<Post> findByPostNo(Long postNo);
}
