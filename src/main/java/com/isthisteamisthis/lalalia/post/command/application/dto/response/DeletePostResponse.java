package com.isthisteamisthis.lalalia.post.command.application.dto.response;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DeletePostResponse {

    private final Long postId;

    public static DeletePostResponse from (Long postId){
        return new DeletePostResponse(
                postId
        );
    }
}