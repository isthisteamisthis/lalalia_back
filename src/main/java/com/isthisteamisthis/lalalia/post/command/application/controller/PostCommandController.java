package com.isthisteamisthis.lalalia.post.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.post.command.application.dto.request.CreatePostRequest;
import com.isthisteamisthis.lalalia.post.command.application.dto.response.CreatePostResponse;
import com.isthisteamisthis.lalalia.post.command.application.dto.response.DeletePostResponse;
import com.isthisteamisthis.lalalia.post.command.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.post.command.application.service.PostCommandService;
import com.isthisteamisthis.lalalia.post.command.infrastructure.service.ApiUserPostCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "게시물 Command API")
@RestController
@RequiredArgsConstructor
public class PostCommandController {

    private final PostCommandService postCommandService;
    private final ApiUserPostCommandService apiUserPostCommandService;

    // 게시물 등록
    @Operation(summary = "게시물 등록")
    @PostMapping("/posts")
    public ResponseEntity<ApiResponse> createPost(@RequestHeader Map<String, String> requestHeader,
                                                  @RequestBody CreatePostRequest createPostRequest) {

        // jwt 토큰을 이용해서 사용자 정보 가져오기
        UserResponse userResponse = apiUserPostCommandService.getUser(requestHeader.get("authorization"));

        // 게시물 등록
        CreatePostResponse createPostResponse = postCommandService.createPost(userResponse, createPostRequest);

        return ResponseEntity.ok(ApiResponse.success("게시물 등록 성공", createPostResponse));
    }

    // 게시물 삭제
    @Operation(summary = "게시물 삭제")
    @DeleteMapping("/posts/{postNo}")
    public ResponseEntity<ApiResponse> deleteMyPost(@RequestHeader Map<String, String> requestHeader,
                                                    @PathVariable("postNo") Long postNo ) {
        // user 정보 조회
        UserResponse user = apiUserPostCommandService.getUser(requestHeader.get("authorization"));
        // 게시물 삭제
        DeletePostResponse deletePostResponse = postCommandService.deletePost(user, postNo);

        return ResponseEntity.ok(ApiResponse.success("게시물 삭제 성공", deletePostResponse));

    }

}
