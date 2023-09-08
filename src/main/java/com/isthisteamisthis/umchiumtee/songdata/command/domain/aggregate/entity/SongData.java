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
    private Float songLength;

    @Column
    private Float maxFreq;

    @Column
    private Float minFreq;

    @Column
    private String mrFile;

    @Column
    private String originalFile;

    @Builder
    public SongData(Long songDataNo, String songName, Float songLength, Float maxFreq, Float minFreq, String mrFile, String originalFile) {
        this.songDataNo = songDataNo;
        this.songName = songName;
        this.songLength = songLength;
        this.maxFreq = maxFreq;
        this.minFreq = minFreq;
        this.mrFile = mrFile;
        this.originalFile = originalFile;
    }
}
