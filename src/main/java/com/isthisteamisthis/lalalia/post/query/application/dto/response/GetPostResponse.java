package com.isthisteamisthis.lalalia.post.query.application.dto.response;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.ComposeSongVO;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.PerfectScoreVO;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetPostResponse {

    private final Long postNo;
    private final Date date;
    private final int likeCnt;
    private final String title;
    private final String content;
    private final UserNoVO userNoVO;
    private final ComposeSongVO composeSongVO;
    private final PerfectScoreVO perfectScoreVO;
    private final boolean isMe;

    public static GetPostResponse from(Post post, boolean isMe) {
        return new GetPostResponse(
                post.getPostNo(),
                post.getDate(),
                post.getLikeCnt(),
                post.getTitle(),
                post.getContent(),
                post.getUserNoVO(),
                post.getComposeSongVO(),
                post.getPerfectScoreVO(),
                isMe
        );

    }

}
