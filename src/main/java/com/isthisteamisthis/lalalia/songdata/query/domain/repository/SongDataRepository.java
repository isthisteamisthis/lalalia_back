package com.isthisteamisthis.lalalia.songdata.query.domain.repository;

import com.isthisteamisthis.lalalia.songdata.query.domain.aggregate.entity.SongData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongDataRepository extends JpaRepository<SongData, Long> {

    SongData findBySongName(String songName);
}
