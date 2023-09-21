package com.isthisteamisthis.lalalia.post.query.application.dto.response;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetAllPostsResponse {

    private final Long postNo;
    private final Long userNo;
    private final Long composeSongNo;
    private final Long perfectScoreNo;
    private final String imgFile;
    private final String nickname;
    private final String title;
    private final String content;
    private final Date date;
    private final int likeCnt;

    public static GetAllPostsResponse from(Post post, String nickname){
        return new GetAllPostsResponse(
                post.getPostNo(),
                post.getUserNoVO().getUserNo(),
                post.getComposeSongVO() != null ? post.getComposeSongVO().getComposeSongNo() : null,
                post.getPerfectScoreVO() != null ? post.getPerfectScoreVO().getPerfectScoreNo() : null,
                post.getImgFile(),
                nickname,
                post.getTitle() != null ? post.getTitle() : null,
                post.getContent(),
                post.getDate(),
                post.getLikeCnt()
        );
    }

}