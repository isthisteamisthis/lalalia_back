package com.isthisteamisthis.umchiumtee.user.command.application.dto.response;

import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.umchiumtee.user.command.domain.aggregate.vo.RangeVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MaxVoiceRangeResponse {

    private final Long userNo;
    private final RangeVO maxRangeVO;

    public static MaxVoiceRangeResponse from(User user) {
        return new MaxVoiceRangeResponse(
                user.getUserNo(),
                user.getMaxRange()
        );
    }

}
