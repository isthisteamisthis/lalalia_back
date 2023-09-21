package com.isthisteamisthis.lalalia.composesong.query.application.dto.resposne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class FindComposeSongListResponse {

    @JsonProperty("compose_song_list")
    private final List<ComposeSong> composeSongList;

    public static FindComposeSongListResponse from(List<ComposeSong> composeSongList) {
        return new FindComposeSongListResponse(
                composeSongList
        );
    }
}
