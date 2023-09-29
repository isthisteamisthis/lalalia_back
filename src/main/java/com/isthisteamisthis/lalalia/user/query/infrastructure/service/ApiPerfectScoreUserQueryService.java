package com.isthisteamisthis.lalalia.user.query.infrastructure.service;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.user.query.infrastructure.repository.ApiPerfectScoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiPerfectScoreUserQueryService {

    private final ApiPerfectScoreQueryRepository apiPerfectScoreQueryRepository;

    // userNo로 perfectScore List 조회
    @Transactional(readOnly = true)
    public List<PerfectScore> getMyPerfectScoreList(Long userNo) {

        // userId로 userNoVO 생성
        UserNoVO userNoVO = new UserNoVO(userNo);
        // userNo로 연관된 perfect score list 조회해서 반환
        return apiPerfectScoreQueryRepository.findPerfectScoresByUserNoVO(userNoVO);

    }

}
