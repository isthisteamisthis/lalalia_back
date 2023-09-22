package com.isthisteamisthis.lalalia.perfectscore.query.application.service;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreListResponse;
import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreResponse;
import com.isthisteamisthis.lalalia.perfectscore.query.domain.repository.PerfectScoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfectScoreQueryService {

    private final PerfectScoreQueryRepository perfectScoreQueryRepository;

    @Transactional(readOnly = true)
    public List<FindPerfectScoreListResponse> getPerfectScoreListByUserNoVO(UserNoVO userNoVO) {

        List<PerfectScore> perfectScoreList = perfectScoreQueryRepository.findPerfectScoresByUserNoVO(userNoVO);

        return getPerfectScoreListResponse(perfectScoreList);

    }

    public List<FindPerfectScoreListResponse> getPerfectScoreListResponse(List<PerfectScore> perfectScoreList) {
        List<FindPerfectScoreListResponse> response = perfectScoreList.stream().map(
                perfectScore -> {

                    Long perfectScoreNo = perfectScore.getPerfectScoreNo();
                    Long userNo = perfectScore.getUserNoVO().getUserNo();
                    String songName = perfectScore.getSongName();
                    String imgFile = perfectScore.getImgFile();
                    Float score = perfectScore.getScore();

                    return FindPerfectScoreListResponse.from(perfectScoreNo, userNo, songName, imgFile, score);

                }).collect(Collectors.toList());

        return response;
    }
    @Transactional(readOnly = true)
    public FindPerfectScoreResponse getPerfectScoreByNo(Long perfectScoreNo) {
        Optional<PerfectScore> optionalPerfectScore = perfectScoreQueryRepository.findById(perfectScoreNo);

        if(optionalPerfectScore.isPresent()) {
            PerfectScore perfectScore = optionalPerfectScore.get();
            return FindPerfectScoreResponse.from(perfectScore);
        }
        else throw new IllegalArgumentException("일치하는 퍼펙트 스코어가 없습니다.");

    }


}
