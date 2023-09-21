package com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.UserNoVO;
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
    private String wavFile;

    @Builder
    public PerfectScore(UserNoVO userNoVO, String songName, Float score, String wavFile) {
        this.userNoVO = userNoVO;
        this.songName = songName;
        this.score = score;
        this.wavFile = wavFile;
    }

}
