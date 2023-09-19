package com.isthisteamisthis.lalalia.composesong.command.domain.repository;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposeSongCommandRepository extends JpaRepository<ComposeSong, Long> {
}
