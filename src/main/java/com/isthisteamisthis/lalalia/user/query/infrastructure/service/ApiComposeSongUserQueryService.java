package com.isthisteamisthis.lalalia.user.query.infrastructure.service;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.user.query.infrastructure.repository.ApiComposeSongUserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiComposeSongUserQueryService {

    private final ApiComposeSongUserQueryRepository apiComposeSongUserQueryRepository;

    @Transactional
    public List<ComposeSong> getMyComposeSongList(Long userId) {

        // userId로 userNoVO 생성
        UserNoVO userNoVO = new UserNoVO(userId);
        // userNo로 연관된 AI 데모곡 리스트 조회해서 반환
        return apiComposeSongUserQueryRepository.findComposeSongsByUserNoVO(userNoVO);

    }

}
