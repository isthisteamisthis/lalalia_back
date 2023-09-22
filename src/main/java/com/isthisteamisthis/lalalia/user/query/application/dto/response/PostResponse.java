package com.isthisteamisthis.lalalia.user.query.application.dto.response;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PostResponse {

    private final Long postNo;
    private final Date date;
    private final int likeCnt;
    private final String title;
    private final String content;
    private final String imgFile;
    private final Long userNo;
    private final Long composeSongNo;
    private final Long perfectScoreNo;

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getPostNo(),
                post.getDate(),
                post.getLikeCnt(),
                post.getTitle(),
                post.getContent(),
                post.getImgFile(),
                post.getUserNoVO().getUserNo(),
                post.getComposeSongVO() != null ? post.getComposeSongVO().getComposeSongNo() : null,
                post.getPerfectScoreVO() != null ? post.getPerfectScoreVO().getPerfectScoreNo() : null
        );
    }
}
