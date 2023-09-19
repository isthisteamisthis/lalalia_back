package com.isthisteamisthis.lalalia.like.command.application.dto.response;

import com.isthisteamisthis.lalalia.like.command.domain.aggregate.vo.LikeVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DeleteLikeResponse {

    private final Long postNo;
    private final Long userNo;

    public static DeleteLikeResponse from(LikeVO likeVO) {
        return new DeleteLikeResponse(
                likeVO.getPostNo(),
                likeVO.getUserNo()
        );
    }

}
