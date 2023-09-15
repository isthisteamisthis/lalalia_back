package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateRangeSongResponse implements Serializable {

    private final Map<String, String> recommendSongs;

    public Map<String, String> getRecommendSongs() {
        return recommendSongs;
    }
}
