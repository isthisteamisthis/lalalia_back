package com.isthisteamisthis.lalalia.like.query.domain.repository;

import com.isthisteamisthis.lalalia.like.command.domain.aggregate.entity.Like;
import com.isthisteamisthis.lalalia.like.command.domain.aggregate.vo.LikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeQueryRepository extends JpaRepository<Like, LikeVO> {

    Optional<Like> findByLikeVO(LikeVO likeVO);
}
