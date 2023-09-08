package com.isthisteamisthis.umchiumtee.usersong.command.domain.aggregate.entity;

import com.isthisteamisthis.umchiumtee.usersong.command.domain.aggregate.vo.SongDataNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_USER_SONG")
public class UserSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSongNo;

    @Column
    private Long userNo;


    @Column
    private String songName;

    @Column
    private Float score;

    @Column
    private Float songLength;

    @Column
    private String wavFile;

    @Embedded
    private SongDataNoVO songDataNoVO;

    @Builder
    public UserSong(Long userSongNo, Long userNo, String songName, Float score, Float songLength, String wavFile, SongDataNoVO songDataNoVO) {
        this.userSongNo = userSongNo;
        this.userNo = userNo;
        this.songName = songName;
        this.score = score;
        this.songLength = songLength;
        this.wavFile = wavFile;
        this.songDataNoVO = songDataNoVO;
    }

}
