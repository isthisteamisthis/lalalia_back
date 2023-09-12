package com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity;

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
    private String userId;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String userIntro;

    @Column
    private Float avgScore;

    @Column
    private Float maxRange;

    @Column
    private Float minRange;

    @Column
    private String category;

    @Builder
    public User(Long userNo, String userId, String password, String email, String userIntro, Float avgScore, Float maxRange, Float minRange, String category) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.userIntro = userIntro;
        this.avgScore = avgScore;
        this.maxRange = maxRange;
        this.minRange = minRange ;
        this.category = category;
    }


}
