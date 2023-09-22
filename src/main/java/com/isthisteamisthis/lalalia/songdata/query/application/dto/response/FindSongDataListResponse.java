package com.isthisteamisthis.lalalia.songdata.query.application.dto.response;

import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreListResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class FindSongDataListResponse {

    private final Long songDataNo;
    private final String songName;
    private final String artistName;
    private final String imgUrl;

    public static FindSongDataListResponse from(Long songDataNo,String songName, String artistName, String imgUrl) {
        return new FindSongDataListResponse(
                songDataNo,
                songName,
                artistName,
                imgUrl
        );
    }
}
