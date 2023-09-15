package com.isthisteamisthis.lalalia.post.query.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetUserPostResponse {

    @JsonProperty("post_list")
    private final List<Post> postList;

    public static GetUserPostResponse from(List<Post> postList) {
        return new GetUserPostResponse(
                postList
        );
    }

}