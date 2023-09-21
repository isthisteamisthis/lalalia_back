package com.isthisteamisthis.lalalia.message.query.application.dto.response;

import com.isthisteamisthis.lalalia.message.command.domain.aggregate.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetMessageListResponse {

    private final Long messageNo;
    private final Long sendUserNo;
    private final String sendUserNickname;
    private final Long getUserNo;
    private final String getUserNickname;
    private final String content;
    private final Date date;

    public static GetMessageListResponse from(Message message, String sendUserNickname, String getUserNickname) {
        return new GetMessageListResponse(
                message.getMessageNo(),
                message.getSendUserNoVO().getSendUserNo(),
                sendUserNickname,
                message.getGetUserNoVO().getGetUserNo(),
                getUserNickname,
                message.getContent(),
                message.getDate()
        );
    }
}
