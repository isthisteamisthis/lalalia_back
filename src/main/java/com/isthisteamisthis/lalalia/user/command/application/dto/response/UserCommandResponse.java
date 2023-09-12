package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCommandResponse {
    private final Long userNo;
    private final String userId;
    private final String password;
    private final String email;
    private final String userIntro;
    private final Float avgScore;
    private final String category;


    public static UserCommandResponse from(User user) {
        return new UserCommandResponse(
                user.getUserNo(),
                user.getUserId(),
                user.getPassword(),
                user.getEmail(),
                user.getUserIntro(),
                user.getAvgScore(),
                user.getCategory()
        );
    }

    public Long getUserNo() {
        return userNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
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
