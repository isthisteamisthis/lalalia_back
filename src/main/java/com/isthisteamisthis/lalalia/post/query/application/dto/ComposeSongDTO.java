package com.isthisteamisthis.lalalia.post.query.application.dto;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ComposeSongDTO {
    UUID identifier;
    String title;
    String imgFile;
    String aiSongFile;
    Long userNo;

    public ComposeSongDTO(ComposeSong composeSong) {
        this.identifier = composeSong.getIdentifier();
        this.title = composeSong.getTitle();
        this.imgFile = composeSong.getImgFile();
        this.aiSongFile = composeSong.getAiSongFile();
        this.userNo = composeSong.getUserNoVO().getUserNo();
    }
}
