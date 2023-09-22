package com.isthisteamisthis.lalalia.songdata.query.application.dto.response;

import com.isthisteamisthis.lalalia.songdata.query.domain.aggregate.entity.SongData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class FindSongDataResponse {

    private final Long songDataNo;
    private final String songName;
    private final String artistName;
    private final String imgUrl;
    private final String mrUrl;
    private final String lyricUrl;

    public static FindSongDataResponse from(SongData songData) {
        return new FindSongDataResponse(
                songData.getSongDataNo(),
                songData.getSongName(),
                songData.getArtistName(),
                songData.getImageUrl(),
                songData.getMrUrl(),
                songData.getLyricUrl()
        );
    }

}
