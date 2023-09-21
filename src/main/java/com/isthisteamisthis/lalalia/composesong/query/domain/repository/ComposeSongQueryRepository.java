package com.isthisteamisthis.lalalia.composesong.query.domain.repository;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComposeSongQueryRepository extends JpaRepository<ComposeSong, Long> {
    List<ComposeSong> findComposeSongsByUserNoVO(UserNoVO userNoVO);
}
