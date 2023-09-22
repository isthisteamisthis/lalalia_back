package com.isthisteamisthis.lalalia.post.query.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetUserPostResponse {

    private final Long postNo;
    private final Long userNo;
    private final Long composeSongNo;
    private final Long perfectScoreNo;
    private final String imgFile;
    private final String title;
    private final String content;
    private final Date date;
    private final int likeCnt;

    public static GetUserPostResponse from(Post post) {
        return new GetUserPostResponse(
                post.getPostNo(),
                post.getUserNoVO().getUserNo(),
                post.getComposeSongVO() != null ? post.getComposeSongVO().getComposeSongNo() : null,
                post.getPerfectScoreVO() != null ? post.getPerfectScoreVO().getPerfectScoreNo() : null,
                post.getImgFile(),
                post.getTitle(),
                post.getContent(),
                post.getDate(),
                post.getLikeCnt()
        );
    }

}