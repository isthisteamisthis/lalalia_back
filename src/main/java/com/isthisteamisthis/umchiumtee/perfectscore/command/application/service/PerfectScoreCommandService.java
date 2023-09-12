package com.isthisteamisthis.umchiumtee.perfectscore.command.application.service;

import com.isthisteamisthis.umchiumtee.perfectscore.command.application.dto.request.CreatePerfectScoreRequest;
import com.isthisteamisthis.umchiumtee.perfectscore.command.application.dto.response.PerfectScoreCommandResponse;
import com.isthisteamisthis.umchiumtee.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.umchiumtee.perfectscore.command.domain.aggregate.vo.SongDataNoVO;
import com.isthisteamisthis.umchiumtee.perfectscore.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.umchiumtee.perfectscore.command.domain.repository.PerfectScoreCommandRespository;
import com.isthisteamisthis.umchiumtee.perfectscore.command.infrastructure.service.PerfectScoreInfraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

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
