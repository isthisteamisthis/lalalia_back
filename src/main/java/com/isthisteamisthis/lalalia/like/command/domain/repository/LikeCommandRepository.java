package com.isthisteamisthis.lalalia.like.command.domain.repository;

import com.isthisteamisthis.lalalia.like.command.domain.aggregate.entity.Like;
import com.isthisteamisthis.lalalia.like.command.domain.aggregate.vo.LikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import javax.websocket.server.PathParam;
import java.util.Optional;

@Repository
public interface LikeCommandRepository extends JpaRepository<Like, LikeVO> {

    // 해당 게시물에서 본인이 좋아요했는지 조회
    Optional<Like> findByLikeVO(LikeVO likeVO);

    // postNo로 Like 삭제
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM TBL_LIKE L WHERE L.post_no = ?1", nativeQuery = true)
    Integer deleteLikesByPostNo(Long postNo);
}
