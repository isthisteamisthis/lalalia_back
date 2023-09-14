package com.isthisteamisthis.lalalia.perfectscore.command.application.service;

import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.request.CreatePerfectScoreRequest;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response.PerfectScoreCommandResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.SongDataNoVO;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.repository.PerfectScoreCommandRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PerfectScoreCommandService {

    private final PerfectScoreCommandRespository perfectScoreCommandRespository;
    @Transactional
    public PerfectScoreCommandResponse createPerfectScore(CreatePerfectScoreRequest request, Float score, String perfectScoreWav) {

        PerfectScore perfectScore = perfectScoreCommandRespository.save(PerfectScore.builder()
                .songDataNoVO(new SongDataNoVO(request.getSongDataNo()))
                .songName(request.getSongName())
                .songLength(request.getSongLength())
                .wavFile(perfectScoreWav)
                .userNoVO(new UserNoVO(request.getUserNo()))
                .score(score)
                .build());

        return PerfectScoreCommandResponse.from(perfectScore);
    }

}
