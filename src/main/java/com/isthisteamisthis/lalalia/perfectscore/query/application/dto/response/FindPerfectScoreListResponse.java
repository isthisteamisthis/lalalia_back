package com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class FindPerfectScoreListResponse {

    private final Long perfectScoreNo;
    private final Long userNo;
    private final String songName;
    private final String imgFile;
    private final Float score;
    public static FindPerfectScoreListResponse from(Long perfectScoreNo, Long userNo, String songName, String imgFile, Float score) {
        return new FindPerfectScoreListResponse(
                perfectScoreNo,
                userNo,
                songName,
                imgFile,
                score
        );
    }
}
