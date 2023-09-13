package com.isthisteamisthis.umchiumtee.user.command.domain.repository;

import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCommandRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(Long userId);

}
