package com.isthisteamisthis.lalalia.message.command.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DeleteMessageResponse {
    // 메세지 삭제에 대한 응답
    private final Long messageNo;
}
