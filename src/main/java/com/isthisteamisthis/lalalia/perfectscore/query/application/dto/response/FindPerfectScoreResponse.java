package com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class FindPerfectScoreResponse {

    private final Long perfectScoreNo;
    private final Long userNo;
    private final String songName;
    private final Float score;
    private final String imgFile;
    private final String wavFile;

    public static FindPerfectScoreResponse from(PerfectScore perfectScore) {
        return new FindPerfectScoreResponse(
            perfectScore.getPerfectScoreNo(),
            perfectScore.getUserNoVO().getUserNo(),
            perfectScore.getSongName(),
            perfectScore.getScore(),
            perfectScore.getImgFile(),
            perfectScore.getWavFile()
        );

    }
}
