package com.isthisteamisthis.lalalia.post.query.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetAllPostsResponse {

    @JsonProperty("post_list")
    private final List<Post> postList;

    public static GetAllPostsResponse from(List<Post> postList){
        return new GetAllPostsResponse(
                postList
        );
    }

}