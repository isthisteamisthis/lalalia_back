package com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@ToString
public class PerfectScoreInfraResponse implements Serializable {

    private final Float score;
    public Float getScore() {
        return score;
    }
}
