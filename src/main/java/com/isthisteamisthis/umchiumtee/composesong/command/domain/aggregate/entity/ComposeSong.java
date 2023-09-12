package com.isthisteamisthis.umchiumtee.composesong.command.domain.aggregate.entity;

import com.isthisteamisthis.umchiumtee.composesong.command.domain.aggregate.vo.UserNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_COMPOSE_SONG")
public class ComposeSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long composeSongNo;

    @Column
    private String title;

    @Column
    private Float songLength;

    @Column
    private String originalFile;

    @Column
    private String aiSongFile;

    @Embedded
    private UserNoVO userNoVO;
    @Builder
    public ComposeSong(Long composeSongNo, String title, Float songLength, String aiSongFile, String originalFile, UserNoVO userNoVO) {
        this. composeSongNo = composeSongNo;
        this.title = title;
        this.songLength = songLength;
        this.originalFile = originalFile;
        this.aiSongFile = aiSongFile;
        this.userNoVO = userNoVO;
    }

}
