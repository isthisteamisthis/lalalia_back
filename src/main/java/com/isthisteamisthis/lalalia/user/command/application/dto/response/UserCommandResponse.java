package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserCommandResponse {
    private final Long userNo;
    private final Long userId;
    private final String nickname;
    private final String email;
    private final String userIntro;
    private final Float avgScore;
    private final String category;


    public static UserCommandResponse from(User user) {
        return new UserCommandResponse(
                user.getUserNo(),
                user.getUserId(),
                user.getNickname(),
                user.getEmail(),
                user.getUserIntro(),
                user.getAvgScore(),
                user.getCategory()
        );
    }

    public Long getUserNo() {
        return userNo;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public Float getAvgScore() {
        return avgScore;
    }

    public String getCategory() {
        return category;
    }

}
