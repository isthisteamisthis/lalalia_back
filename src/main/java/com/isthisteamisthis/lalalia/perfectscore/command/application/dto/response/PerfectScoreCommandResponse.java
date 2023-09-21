package com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PerfectScoreCommandResponse {

    private final Long perfectScoreNo;
    private final Long userNo;
    private final String songName;
    private final Float score;
    private final String wavFile;

    public static PerfectScoreCommandResponse from(PerfectScore perfectScore) {
        return new PerfectScoreCommandResponse(
                perfectScore.getPerfectScoreNo(),
                perfectScore.getUserNoVO().getUserNo(),
                perfectScore.getSongName(),
                perfectScore.getScore(),
                perfectScore.getWavFile()
        );
    }

}
