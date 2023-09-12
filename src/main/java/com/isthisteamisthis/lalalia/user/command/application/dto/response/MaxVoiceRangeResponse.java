package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MaxRangeVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MaxVoiceRangeResponse {

    private final Long userNo;
    private final MaxRangeVO maxRangeVO;

    public static MaxVoiceRangeResponse from(User user) {
        return new MaxVoiceRangeResponse(
                user.getUserNo(),
                user.getMaxRange()
        );
    }

    public Long getUserNo() {
        return userNo;
    }

    public MaxRangeVO getMaxRangeVO() {
        return maxRangeVO;
    }
}
