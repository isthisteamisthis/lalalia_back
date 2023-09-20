package com.isthisteamisthis.lalalia.rangesongdata.query.domain.repository;

import com.isthisteamisthis.lalalia.rangesongdata.query.domain.aggregate.entity.RangeSongData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RangeSongDataRepository extends JpaRepository<RangeSongData, Long> {

    RangeSongData findBySongName(String songName);
}
