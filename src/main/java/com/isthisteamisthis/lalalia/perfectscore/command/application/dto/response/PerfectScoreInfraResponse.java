package com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@ToString
public class PerfectScoreInfraResponse implements Serializable {

    private final String score;
}
