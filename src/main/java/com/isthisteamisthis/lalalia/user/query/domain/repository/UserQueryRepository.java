package com.isthisteamisthis.lalalia.user.query.domain.repository;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserQueryRepository extends JpaRepository<User, Long> {
    // userId로 사용자 조회
    Optional<User> findByUserId(Long userId);
    // userNo로 사용자 조회
    Optional<User> findByUserNo(Long userNo);

    @Query(value = "SELECT U.NICKNAME FROM TBL_USER U WHERE U.USER_NO = ?1", nativeQuery = true)
    String getNicknameByUserNo(Long userNo);
}
