package com.isthisteamisthis.umchiumtee.usersong.command.domain.aggregate.entity;

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
    private Long songDataNo;

    @Column
    private String songName;

    @Column
    private Float score;

    @Column
    private Float songLength;

    @Column
    private String wavFile;

    @Builder
    public UserSong(Long userSongNo, Long userNo, Long songDataNo, String songName, Float score, Float songLength, String wavFile, String avg) {
        this.userSongNo = userSongNo;
        this.userNo = userNo;
        this.songDataNo = songDataNo;
        this.songName = songName;
        this.score = score;
        this.songLength = songLength;
        this.wavFile = wavFile;
    }

}
