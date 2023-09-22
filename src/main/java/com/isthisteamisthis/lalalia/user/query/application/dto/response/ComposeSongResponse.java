package com.isthisteamisthis.lalalia.user.query.application.dto.response;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ComposeSongResponse {

    private final Long composeSongNo;
    private final UUID identifier;
    private final String title;
    private final String imgFile;
    private final String originalFile;
    private final String aiSongFile;
    private final Long userNo;

    public static ComposeSongResponse from(ComposeSong composeSong) {
        return new ComposeSongResponse(
                composeSong.getComposeSongNo(),
                composeSong.getIdentifier(),
                composeSong.getTitle(),
                composeSong.getImgFile(),
                composeSong.getOriginalFile(),
                composeSong.getAiSongFile(),
                composeSong.getUserNoVO().getUserNo()
        );
    }
}
