package com.isthisteamisthis.lalalia.post.query.application.dto.response;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetPostResponse {

    private final Long postNo;
    private final Date date;
    private final int likeCnt;
    private final String title;
    private final String content;
    private final String imgFile;
    private final String aiSongFile;
    private final Long userNo;
    private final Long composeSongNo;
    private final Long perfectScoreNo;
    private final boolean isMe;
    private final boolean like;

    public static GetPostResponse from(Post post, boolean isMe, boolean like, String aiSongFile) {
        return new GetPostResponse(
                post.getPostNo(),
                post.getDate(),
                post.getLikeCnt(),
                post.getTitle() != null ? post.getTitle() : null,
                post.getContent() != null ? post.getContent() : null,
                post.getImgFile(),
                aiSongFile,
                post.getUserNoVO().getUserNo(),
                post.getComposeSongVO() != null ? post.getComposeSongVO().getComposeSongNo() : null,
                post.getPerfectScoreVO() != null ? post.getPerfectScoreVO().getPerfectScoreNo() : null,
                isMe,
                like
        );

    }

}
