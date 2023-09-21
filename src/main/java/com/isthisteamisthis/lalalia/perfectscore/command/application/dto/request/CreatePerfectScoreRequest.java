package com.isthisteamisthis.lalalia.perfectscore.command.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreatePerfectScoreRequest {
    private final String songName;

}
