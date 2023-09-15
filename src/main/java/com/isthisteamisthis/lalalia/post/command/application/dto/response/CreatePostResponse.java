package com.isthisteamisthis.lalalia.post.command.application.dto.response;

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
public class CreatePostResponse {

    private final Long postNo;
    private final Date date;
    private final int likeCnt;
    private final ComposeSongVO composeSongVO;
    private final PerfectScoreVO perfectScoreVO;
    private final UserNoVO userNoVO;
    private final String title;
    private final String content;

    public static CreatePostResponse form(Post post) {
        return new CreatePostResponse(
                post.getPostNo(),
                post.getDate(),
                post.getLikeCnt(),
                post.getComposeSongVO(),
                post.getPerfectScoreVO(),
                post.getUserNoVO(),
                post.getTitle(),
                post.getContent()
        );
    }
}