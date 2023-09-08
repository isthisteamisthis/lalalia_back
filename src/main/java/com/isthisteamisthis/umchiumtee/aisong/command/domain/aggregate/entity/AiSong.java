package com.isthisteamisthis.umchiumtee.aisong.command.domain.aggregate.entity;

import com.isthisteamisthis.umchiumtee.aisong.command.domain.aggregate.vo.SongDataNoVO;
import com.isthisteamisthis.umchiumtee.aisong.command.domain.aggregate.vo.UserNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_AISONG")
public class AiSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aiSongNo;

    @Column
    private String title;

    @Column
    private Float songLength;

    @Column
    private String aiSongFile;

    @Embedded
    private SongDataNoVO songDataNoVO;

    @Embedded
    private UserNoVO userNoVO;

    @Builder
    public AiSong(Long aiSongNo, String title, Float songLength, String aiSongFile, SongDataNoVO songDataNoVO, UserNoVO userNoVO) {
        this. aiSongNo = aiSongNo;
        this.title = title;
        this.songLength = songLength;
        this.aiSongFile = aiSongFile;
        this.songDataNoVO = songDataNoVO;
        this.userNoVO = userNoVO;
    }

}
