package com.isthisteamisthis.lalalia.composesong.command.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@ToString
@Setter
@Getter
public class CreateComposeSongRequest{
    String name;

    String model;

    String octave;

    String index;

    public CreateComposeSongRequest(String name, String model, String octave, String index) {
        this.name = name;
        this.model = model;
        this.octave = octave;
        this.index = index;
    }
}
