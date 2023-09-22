package com.isthisteamisthis.lalalia.post.command.application.dto.response;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserResponse {

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
    private final String minNote;
    private final String minOctave;

    public static UserResponse from(User user) {
        return new UserResponse(
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
                user.getMinRange() != null ? user.getMinRange().getMinOctave() : null
        );
    }

}
