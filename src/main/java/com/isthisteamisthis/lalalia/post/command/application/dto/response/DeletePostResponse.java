package com.isthisteamisthis.lalalia.post.command.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DeletePostResponse {

    private final Long postNo;
    private final Integer deleteCnt;

    public static DeletePostResponse from (Long postNo, Integer deleteLikeCnt){
        return new DeletePostResponse(
                postNo,
                deleteLikeCnt
        );
    }
}