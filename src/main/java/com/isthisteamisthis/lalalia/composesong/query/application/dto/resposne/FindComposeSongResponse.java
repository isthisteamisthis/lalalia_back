package com.isthisteamisthis.lalalia.composesong.query.application.dto.resposne;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindComposeSongResponse {

    private final Long composeSongNo;
    private final Long userNo;
    private final String title;
    private final String imgFile;
    private final String originalFile;
    private final String aiSongFile;

    public static FindComposeSongResponse from(ComposeSong composeSong) {
        return new FindComposeSongResponse(
                composeSong.getUserNoVO().getUserNo(),
                composeSong.getComposeSongNo(),
                composeSong.getTitle(),
                composeSong.getImgFile(),
                composeSong.getOriginalFile(),
                composeSong.getAiSongFile()
        );
    }
}
