package com.isthisteamisthis.lalalia.post.command.application.dto.response;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreatePostResponse {
    // post 생성에 대한 응답
    private final Long postNo;
    private final Date date;
    private final int likeCnt;
    private final Long composeSongNo;
    private final Long perfectScoreNo;
    private final Long userNo;
    private final String title;
    private final String content;
    private final String imgFile;

    public static CreatePostResponse from(Post post) {
        return new CreatePostResponse(
                post.getPostNo(),
                post.getDate(),
                post.getLikeCnt(),
                post.getComposeSongVO() != null ? post.getComposeSongVO().getComposeSongNo() : null,
                post.getPerfectScoreVO() != null ? post.getPerfectScoreVO().getPerfectScoreNo() : null,
                post.getUserNoVO().getUserNo(),
                post.getTitle(),
                post.getContent(),
                post.getImgFile()
        );
    }
}