package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MaxRangeVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
//@NoArgsConstructor(force = true)
@ToString
public class MaxVoiceRangeResponse implements Serializable {

    private final String highestfrequency;
    private final String note;
    private final String octave;

    public String getHighestfrequency() {
        return highestfrequency;
    }

    public String getNote() {
        return note;
    }

    public String getOctave() {
        return octave;
    }
}
