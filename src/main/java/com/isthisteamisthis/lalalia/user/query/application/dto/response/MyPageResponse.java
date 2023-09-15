package com.isthisteamisthis.lalalia.user.query.application.dto.response;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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


    public static MyPageResponse from(User user) {
        return new MyPageResponse(
                user.getUserNo(),
                user.getUserId(),
                user.getNickname(),
                user.getEmail(),
                user.getCategory(),
                user.getUserIntro(),
                user.getAvgScore(),
                user.getMaxRange().getMaxFrequency(),
                user.getMaxRange().getMaxNote(),
                user.getMaxRange().getMaxOctave(),
                user.getMinRange().getMinFrequency(),
                user.getMinRange().getMinNote(),
                user.getMinRange().getMinOctave()
        );
    }

}
