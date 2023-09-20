package com.isthisteamisthis.lalalia.post.query.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.post.command.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetAllPostsResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetMyPagePostResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetPostResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetUserPostResponse;
import com.isthisteamisthis.lalalia.post.query.application.service.PostQueryService;
import com.isthisteamisthis.lalalia.post.query.infrastructure.service.ApiUserPostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryService postQueryService;
    private final ApiUserPostQueryService apiUserPostQueryService;

    // 한 유저의 게시물 전체 조회
    @GetMapping("my-page/posts")
    public ResponseEntity<ApiResponse> getPostsByUserNo(@RequestHeader Map<String, String> requestHeader) {

        UserResponse user = apiUserPostQueryService.getUser(requestHeader.get("authorization"));

        GetUserPostResponse getUserPostResponse = postQueryService.getPostsByUserNo(user);

        return ResponseEntity.ok(ApiResponse.success("사용자의 게시물 전체 조회 성공", getUserPostResponse));

    }

    // 게시물 전체 조회 : 좋아요 정렬
    @GetMapping("/posts")
    public ResponseEntity<ApiResponse> getAllPosts(@RequestHeader Map<String, String> requestHeader) {

        GetAllPostsResponse getAllPostsResponse = postQueryService.getAllPosts();

        return ResponseEntity.ok(ApiResponse.success("게시물 전체 조회 성공", getAllPostsResponse));

    }

    // 하나의 내 게시물 상세 조회
    @GetMapping("my-page/posts/{postNo}")
    public ResponseEntity<ApiResponse> getMyPagePost(@RequestHeader Map<String, String> requestHeader, @PathVariable("postNo") Long postNo) {

        UserResponse user = apiUserPostQueryService.getUser(requestHeader.get("authorization"));

        GetMyPagePostResponse getMyPagePostResponse = postQueryService.getMyPagePost(user, postNo);

        return ResponseEntity.ok(ApiResponse.success("My Page : 게시물 상세 조회 성공", getMyPagePostResponse));

    }


    // 하나의 게시물 상세 조회
    @GetMapping("posts/{postNo}")
    public ResponseEntity<ApiResponse> getPost(@RequestHeader Map<String, String> requestHeader,
                                               @PathVariable("postNo") Long postNo) {
        UserResponse user = apiUserPostQueryService.getUser(requestHeader.get("authorization"));

        GetPostResponse getPostResponse = postQueryService.getCommunityPost(user, postNo);

        return ResponseEntity.ok(ApiResponse.success("Community : 게시물 상세 조회 성공", getPostResponse));

    }

}
