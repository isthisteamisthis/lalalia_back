package com.isthisteamisthis.lalalia.message.query.domain.repository;

import com.isthisteamisthis.lalalia.message.command.domain.aggregate.entity.Message;
import com.isthisteamisthis.lalalia.message.command.domain.aggregate.vo.GetUserNoVO;
import com.isthisteamisthis.lalalia.message.command.domain.aggregate.vo.SendUserNoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageQueryRepository extends JpaRepository<Message, Long> {

    // 하나의 메세지 조회
    @Query(value = "SELECT * FROM TBL_MESSAGE M WHERE MESSAGE_NO=?1", nativeQuery = true)
    Optional<Message> findByMessageNo(Long messageNo);

    // 사용자가 받은 메세지 리스트 조회 : 날짜 내림차순 (최신순)
    List<Message> findMessagesByGetUserNoVOOrderByDateDesc(GetUserNoVO getUserNoVO);

    // 사용자가 보낸 메세지 리스트 조회 : 날짜 내림차순 (최신순)
    List<Message> findMessagesBySendUserNoVOOrderByDateDesc(SendUserNoVO snedUserNoVO);

}
