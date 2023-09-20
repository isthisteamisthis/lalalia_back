package com.isthisteamisthis.lalalia.user.query.infrastructure.repository;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.vo.UserNoVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiComposeSongQueryRepository extends JpaRepository<ComposeSong, Long> {

    List<ComposeSong> findComposeSongsByUserNoVO(UserNoVO userNoVO);

}
