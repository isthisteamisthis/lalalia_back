package com.isthisteamisthis.umchiumtee.perfectscore.command.domain.aggregate.entity;

import com.isthisteamisthis.umchiumtee.perfectscore.command.domain.aggregate.vo.SongDataNoVO;
import com.isthisteamisthis.umchiumtee.perfectscore.command.domain.aggregate.vo.UserNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_PERFECT_SCORE")
public class PerfectScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perfectScoreNo;

    @Column
    private UserNoVO userNoVO;

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
    public PerfectScore(Long perfectScoreNo, UserNoVO userNoVO, String songName, Float score, Float songLength, String wavFile, SongDataNoVO songDataNoVO) {
        this.perfectScoreNo = perfectScoreNo;
        this.userNoVO = userNoVO;
        this.songName = songName;
        this.score = score;
        this.songLength = songLength;
        this.wavFile = wavFile;
        this.songDataNoVO = songDataNoVO;
    }

}
