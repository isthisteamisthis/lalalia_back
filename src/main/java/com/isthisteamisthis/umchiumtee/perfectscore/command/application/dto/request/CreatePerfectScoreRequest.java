package com.isthisteamisthis.umchiumtee.perfectscore.command.application.dto.request;

import com.isthisteamisthis.umchiumtee.perfectscore.command.domain.aggregate.vo.SongDataNoVO;
import com.isthisteamisthis.umchiumtee.perfectscore.command.domain.aggregate.vo.UserNoVO;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;

@RequiredArgsConstructor
public class CreatePerfectScoreRequest {
    private final Long userNo;
    private final String songName;
    private final Float score;
    private final Float songLength;
    private final String wavFile;
    private final Long songDataNo;

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
