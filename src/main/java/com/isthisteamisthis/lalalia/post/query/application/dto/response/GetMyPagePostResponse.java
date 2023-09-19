package com.isthisteamisthis.lalalia.post.query.application.dto.response;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.ComposeSongVO;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.PerfectScoreVO;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetMyPagePostResponse {

    private final Long postNo;
    private final Date date;
    private final int likeCnt;
    private final String title;
    private final String content;
    private final UserNoVO userNoVO;
    private final ComposeSongVO composeSongVO;
    private final PerfectScoreVO perfectScoreVO;
    private final boolean like;

    public static GetMyPagePostResponse from(Post post, boolean like) {
        return new GetMyPagePostResponse(
                post.getPostNo(),
                post.getDate(),
                post.getLikeCnt(),
                post.getTitle() != null ? post.getTitle() : null,
                post.getContent() != null ? post.getContent() : null,
                post.getUserNoVO(),
                post.getComposeSongVO() != null ? post.getComposeSongVO() : null,
                post.getPerfectScoreVO() != null ? post.getPerfectScoreVO() : null,
                like
        );

    }

}
