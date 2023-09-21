package com.isthisteamisthis.lalalia.composesong.command.domain.repository;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComposeSongCommandRepository extends JpaRepository<ComposeSong, Long> {

    Optional<ComposeSong> findComposeSongByIdentifier(UUID identifier);
}
