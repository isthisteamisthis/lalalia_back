package com.isthisteamisthis.umchiumtee.post.command.domain.aggregate.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "TBL_POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    @Column
    private Long userNo;

    @Column
    private Long aiSongNo;

    @Column
    private Date date;

    @Column
    private int likeCnt;

    @Column
    private String title;

    @Column
    private String content;

    @Builder
    public Post(Long postNo, Long userNo, Long aiSongNo, Date date, int likeCnt, String title, String content) {
        this.postNo = postNo;
        this.userNo = userNo;
        this.aiSongNo = aiSongNo;
        this.date = date;
        this.likeCnt = likeCnt;
        this.title = title;
        this.content = content;
    }

}
