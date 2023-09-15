package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateRangeSongResponse implements Serializable {

    private final List<String> matchingfilenames;

    public List<String> getMatchingfilenames() {
        return matchingfilenames;
    }
}
