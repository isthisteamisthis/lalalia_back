package com.isthisteamisthis.lalalia.user.command.application.dto.request;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VoiceRangeRequest {

    private final Long userNo;

    public Long getUserNo() {
        return userNo;
    }

}
