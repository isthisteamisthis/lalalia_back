package com.isthisteamisthis.umchiumtee.songdata.command.domain.aggregate.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_SONG_DATA")
public class SongData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songDataNo;

    @Column
    private String songName;

    @Column
    private String songSinger;

    @Column
    private Float songLength;

    @Column
    private Float maxFreq;

    @Column
    private Float minFreq;

    @Column
    private String mrFile;

    @Column
    private String originalFile;

    @Column
    private String imageUrl;

    @Builder
    public SongData(Long songDataNo, String songName, String songSinger, Float songLength, Float maxFreq, Float minFreq, String mrFile, String originalFile, String imageUrl) {
        this.songDataNo = songDataNo;
        this.songName = songName;
        this.songSinger = songSinger;
        this.songLength = songLength;
        this.maxFreq = maxFreq;
        this.minFreq = minFreq;
        this.mrFile = mrFile;
        this.originalFile = originalFile;
        this.imageUrl = imageUrl;
    }
}
