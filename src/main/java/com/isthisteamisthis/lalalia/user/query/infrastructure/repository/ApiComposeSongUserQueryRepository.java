package com.isthisteamisthis.lalalia.user.query.infrastructure.repository;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.vo.UserNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiComposeSongUserQueryRepository extends JpaRepository<ComposeSong, Long> {

    // Ai 데모곡 리스트 조회
    List<ComposeSong> findComposeSongsByUserNoVO(UserNoVO userNoVO);

}
