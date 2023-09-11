package com.isthisteamisthis.umchiumtee.user.command.application.dto.response;

import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity.User;

import javax.persistence.Column;

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

    public UserCommandResponse(Long userNo, String userId, String password, String email, String userIntro, Float avgScore, Float minRange, Float maxRange, String category) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.userIntro = userIntro;
        this.avgScore = avgScore;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.category = category;
    }

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

    public Float getMaxRange() {
        return maxRange;
    }

    public String getCategory() {
        return category;
    }

}
