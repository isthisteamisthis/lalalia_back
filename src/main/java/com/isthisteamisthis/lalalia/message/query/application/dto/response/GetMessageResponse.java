package com.isthisteamisthis.lalalia.message.query.application.dto.response;

import com.isthisteamisthis.lalalia.message.command.domain.aggregate.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetMessageResponse {
    private final Long messageNo;
    private final String content;
    private final Date date;
    private final Long getUserNo;
    private final String getUserNickname;
    private final Long sendUserNo;
    private final String sendUserNickname;

    public static GetMessageResponse from(Message message, String getUserNickname, String sendUserNickname) {
        return new GetMessageResponse(
                message.getMessageNo(),
                message.getContent(),
                message.getDate(),
                message.getGetUserNoVO().getGetUserNo(),
                getUserNickname,
                message.getSendUserNoVO().getSendUserNo(),
                sendUserNickname
        );
    }
}
