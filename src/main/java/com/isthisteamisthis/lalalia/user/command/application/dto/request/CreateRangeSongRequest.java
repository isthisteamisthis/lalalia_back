package com.isthisteamisthis.lalalia.user.command.application.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

public class CreateRangeSongRequest {

    private final Long userId;

    @JsonCreator
    public CreateRangeSongRequest(@JsonProperty("userId") Long userId) {
        this.userId = userId;
    }


    public Long getUserId() {
        return userId;
    }
}
