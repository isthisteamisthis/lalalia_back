package com.isthisteamisthis.lalalia.user.query.application.dto.response;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MyPageResponse {

    private final Long userNo;
    private final Long userId;
    private final String nickname;
    private final String email;
    private final String category;
    private final String userInfo;
    private final Float avgScore;
    private final Float maxFrequency;
    private final String maxNote;
    private final String maxOctave;
    private final Float minFrequency;
    private final String manNote;
    private final String minOctave;

    private final List<PostResponse> postList;
    private final List<PerfectScoreResponse> perfectScoreList;
    private final List<ComposeSongResponse> composeSongList;


    public static MyPageResponse from(User user, List<PostResponse> postList,
                                      List<PerfectScoreResponse> perfectScoreList,
                                      List<ComposeSongResponse> composeSongList) {
        return new MyPageResponse(
                user.getUserNo(),
                user.getUserId(),
                user.getNickname(),
                user.getEmail() != null ? user.getEmail() : null,
                user.getCategory() != null ? user.getCategory() : null,
                user.getUserIntro(),
                user.getAvgScore(),
                user.getMaxRange() != null ? user.getMaxRange().getMaxFrequency() : null,
                user.getMaxRange() != null ? user.getMaxRange().getMaxNote() : null,
                user.getMaxRange() != null ? user.getMaxRange().getMaxOctave() : null,
                user.getMinRange() != null ? user.getMinRange().getMinFrequency() : null,
                user.getMinRange() != null ? user.getMinRange().getMinNote() : null,
                user.getMinRange() != null ? user.getMinRange().getMinOctave() : null,
                postList,
                perfectScoreList,
                composeSongList
        );
    }

}
