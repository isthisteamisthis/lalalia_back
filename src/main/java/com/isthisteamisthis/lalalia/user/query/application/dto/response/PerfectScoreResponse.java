package com.isthisteamisthis.lalalia.user.query.application.dto.response;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PerfectScoreResponse {

    private final Long perfectScoreNo;
    private final Long userNo;
    private final String songName;
    private final Float score;
    private final String wavFile;
    private final String imgFile;

    public static PerfectScoreResponse from(PerfectScore perfectScore) {
        return new PerfectScoreResponse(
                perfectScore.getPerfectScoreNo(),
                perfectScore.getUserNoVO().getUserNo(),
                perfectScore.getSongName(),
                perfectScore.getScore(),
                perfectScore.getWavFile(),
                perfectScore.getImgFile()
        );
    }

}
