package com.isthisteamisthis.umchiumtee.post.command.domain.aggregate.entity;

import com.isthisteamisthis.umchiumtee.post.command.domain.aggregate.vo.AiSongNoVO;
import com.isthisteamisthis.umchiumtee.post.command.domain.aggregate.vo.UserNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Date date;

    @Column
    private int likeCnt;

    @Column
    private String title;

    @Column
    private String content;

    @Embedded
    private UserNoVO userNoVO;

    @Embedded
    private AiSongNoVO aiSongNoVO;

    @Builder
    public Post(Long postNo, Date date, int likeCnt, String title, String content, UserNoVO userNoVO, AiSongNoVO aiSongNoVO) {
        this.postNo = postNo;
        this.date = date;
        this.likeCnt = likeCnt;
        this.title = title;
        this.content = content;
        this.userNoVO = userNoVO;
        this.aiSongNoVO = aiSongNoVO;
    }

}
