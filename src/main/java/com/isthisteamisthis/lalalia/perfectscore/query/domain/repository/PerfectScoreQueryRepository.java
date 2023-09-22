package com.isthisteamisthis.lalalia.perfectscore.query.domain.repository;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.UserNoVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfectScoreQueryRepository extends JpaRepository<PerfectScore, Long> {
    List<PerfectScore> findPerfectScoresByUserNoVO(UserNoVO userNoVO);
}
