package com.isthisteamisthis.lalalia.composesong.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ComposeSongInfraRequest {

    byte[] music;
    String model;

    String octave;

    String index;

    public ComposeSongInfraRequest(byte[] music, String model, String octave, String index) {
        this.music = music;
        this.model = model;
        this.octave = octave;
        this.index = index;
    }
}
