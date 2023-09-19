package com.isthisteamisthis.lalalia.user.query.infrastructure.repository;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.UserNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiPerfectScoreQueryRepository extends JpaRepository<PerfectScore, Long> {

    // 내가 부른 곡 전체 리스트 조회
    List<PerfectScore> findPerfectScoresByUserNoVO(UserNoVO userNoVO);

}
