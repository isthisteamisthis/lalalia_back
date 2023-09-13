package com.isthisteamisthis.umchiumtee.user.command.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoProfileResponse {

    @Getter
    @JsonProperty("id")
    private Long id;

    @Getter
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonProperty("properties")
    private KakaoProfile kakaoProfile;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoAccount {
        private String email;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoProfile {
        private String nickname;
    }

    public Long getUserId() {
        return getId();
    }

    public String getEmail() {
        return kakaoAccount.getEmail();
    }

    public String getNickname() {
        return kakaoProfile.getNickname();
    }

}
