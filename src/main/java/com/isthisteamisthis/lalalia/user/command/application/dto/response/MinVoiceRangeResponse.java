package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MinVoiceRangeResponse {

    private final Long userNo;
    private final MinRangeVO minRangeVO;

    public static MinVoiceRangeResponse from(User user) {
        return new MinVoiceRangeResponse(
                user.getUserNo(),
                user.getMinRange()
        );
    }

    public Long getUserNo() {
        return userNo;
    }

    public MinRangeVO getMinRangeVO() {
        return minRangeVO;
    }
}
