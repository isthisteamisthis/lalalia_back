package com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PerfectScoreCommandResponse {

    private final Long perfectScoreNo;
    private final Long userNo;
    private final String songName;
    private final Float score;
    private final Float songLength;
    private final String wavFile;
    private final Long songDataNo;

    public static PerfectScoreCommandResponse from(PerfectScore perfectScore) {
        return new PerfectScoreCommandResponse(
                perfectScore.getPerfectScoreNo(),
                perfectScore.getUserNoVO().getUserNo(),
                perfectScore.getSongName(),
                perfectScore.getScore(),
                perfectScore.getSongLength(),
                perfectScore.getWavFile(),
                perfectScore.getSongDataNoVO().getSongDataNo()
        );
    }

    public Long getPerfectScoreNo() {
        return perfectScoreNo;
    }

    public Long getUserNo() {
        return userNo;
    }

    public String getSongName() {
        return songName;
    }

    public Float getScore() {
        return score;
    }

    public Float getSongLength() {
        return songLength;
    }

    public String getWavFile() {
        return wavFile;
    }

    public Long getSongDataNo() {
        return songDataNo;
    }
}
