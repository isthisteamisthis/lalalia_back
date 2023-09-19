package com.isthisteamisthis.lalalia.like.command.application.dto.response;

import com.isthisteamisthis.lalalia.like.command.domain.aggregate.vo.LikeVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateLikeResponse {

    private final Long postNo;
    private final Long userNo;

    public static CreateLikeResponse from(LikeVO likeVO) {
        return new CreateLikeResponse(
                likeVO.getPostNo(),
                likeVO.getUserNo()
        );
    }

}
