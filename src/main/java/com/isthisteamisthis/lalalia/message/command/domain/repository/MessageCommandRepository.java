package com.isthisteamisthis.lalalia.message.command.domain.repository;

import com.isthisteamisthis.lalalia.message.command.domain.aggregate.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageCommandRepository extends JpaRepository<Message, Long> {
    // messageNo으로 message 조회
    Optional<Message> findByMessageNo(Long messageNo);

}
