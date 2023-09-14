package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@ToString
public class MinVoiceRangeResponse implements Serializable {

    private final String lowestfrequency;
    private final String note;
    private final String octave;

    public String getLowestfrequency() {
        return lowestfrequency;
    }

    public String getNote() {
        return note;
    }

    public String getOctave() {
        return octave;
    }
}
