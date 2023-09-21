package com.isthisteamisthis.lalalia.message.command.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DeleteMessageResponse {
    private final Long messageNo;
}
