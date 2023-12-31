package com.isthisteamisthis.lalalia.message.command.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SendMessageRequest {
    // 메세지 생성 request
    private final String content;

}
