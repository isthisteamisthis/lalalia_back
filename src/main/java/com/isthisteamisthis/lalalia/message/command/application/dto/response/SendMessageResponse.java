package com.isthisteamisthis.lalalia.message.command.application.dto.response;

import com.isthisteamisthis.lalalia.message.command.domain.aggregate.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SendMessageResponse {
    // 메세지 생성에 대한 응답
    private final Long getUserNo;
    private final Long sendUserNo;

    public static SendMessageResponse from(Message message) {
        return new SendMessageResponse(
                message.getGetUserNoVO().getGetUserNo(),
                message.getSendUserNoVO().getSendUserNo()
        );
    }

}
