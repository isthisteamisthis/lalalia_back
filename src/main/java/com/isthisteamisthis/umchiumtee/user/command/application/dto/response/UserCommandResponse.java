package com.isthisteamisthis.umchiumtee.user.command.application.dto.response;

import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;

@RequiredArgsConstructor
public class UserCommandResponse {
    private final Long userNo;
    private final String userId;
    private final String password;
    private final String email;
    private final String userIntro;
    private final Float avgScore;
    private final Float minRange;
    private final Float maxRange;
    private final String category;


    public static UserCommandResponse from(User user) {
        return new UserCommandResponse(
                user.getUserNo(),
                user.getUserId(),
                user.getPassword(),
                user.getEmail(),
                user.getUserIntro(),
                user.getAvgScore(),
                user.getMinRange(),
                user.getMaxRange(),
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

    public Float getMinRange() {
        return minRange;
    }

    public Float getMaxRange() { return maxRange; }

    public String getCategory() {
        return category;
    }

}
