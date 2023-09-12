package com.isthisteamisthis.umchiumtee.user.command.application.dto.response;

import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.vo.RangeVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MinVoiceRangeResponse {

    private final Long userNo;
    private final RangeVO minRangeVO;

    public static MinVoiceRangeResponse from(User user) {
        return new MinVoiceRangeResponse(
                user.getUserNo(),
                user.getMinRange()
        );
    }

}
