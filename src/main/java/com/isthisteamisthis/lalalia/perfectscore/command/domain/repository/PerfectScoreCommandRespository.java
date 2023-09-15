package com.isthisteamisthis.lalalia.perfectscore.command.domain.repository;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfectScoreCommandRespository extends JpaRepository<PerfectScore, Long> {
}
