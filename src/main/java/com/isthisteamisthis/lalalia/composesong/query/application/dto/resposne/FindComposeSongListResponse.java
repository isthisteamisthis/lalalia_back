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

    private final Long composeSongNo;
    private final Long userNo;
    private final String title;
    private final String imgFile;


    public static FindComposeSongListResponse from(Long composeSongNo, Long userNo, String title, String imgFile) {
        return new FindComposeSongListResponse(
                composeSongNo,
                userNo,
                title,
                imgFile
        );
    }
}
