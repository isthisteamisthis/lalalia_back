package com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MaxRangeVO;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column
    private Long userId;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String userIntro;

    @Column
    private Float avgScore;

    @Embedded
    private MaxRangeVO maxRange;

    @Embedded
    private MinRangeVO minRange;

    @Column
    private String category;

    @Builder
    public User(Long userNo, Long userId,String nickname, String email, String userIntro, Float avgScore, Float maxRange, Float minRange, String category) {
        this.userNo = userNo;
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.userIntro = userIntro;
        this.avgScore = avgScore;
        this.category = category;
    }

    public void addMaxVoiceRange(MaxRangeVO maxRange) { this.maxRange = maxRange; }

    public void addMinVoiceRange(MinRangeVO minRange) {
        this.minRange = minRange;
    }

    public void addCategory(String category) {
        this.category = category;
    }

    // 자기소개 수정
    public void updateUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    // 닉네임 수정
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateEmail(String email) {
        this.email = email;
    }


}
