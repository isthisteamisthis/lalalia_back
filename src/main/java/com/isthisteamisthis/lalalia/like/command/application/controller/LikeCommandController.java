package com.isthisteamisthis.lalalia.like.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.like.command.application.dto.response.CreateLikeResponse;
import com.isthisteamisthis.lalalia.like.command.application.dto.response.DeleteLikeResponse;
import com.isthisteamisthis.lalalia.like.command.application.service.LikeCommandService;
import com.isthisteamisthis.lalalia.like.command.infrastructure.service.ApiUserLikeCommandService;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "좋아요 Command API")
@RestController
@RequiredArgsConstructor
public class LikeCommandController {

    private final LikeCommandService likeCommandService;
    private final ApiUserLikeCommandService apiUserLikeCommandService;

    // 좋아요 생성
    @Operation(summary = "좋아요 생성")
    @PostMapping("/posts/{postNo}/like")
    public ResponseEntity<ApiResponse> createLike(@RequestHeader Map<String, String> requestHeader,
                                                  @PathVariable("postNo") Long postNo) {

        // 사용자 정보 조회
        UserResponse user = apiUserLikeCommandService.getUser(requestHeader.get("authorization"));
        // LIKE 가 존재하는지 확인하고 LIKE 새로 생성해서 response 반환
        CreateLikeResponse response = likeCommandService.createLikeWithPostNoAndUser(postNo, user);

        return ResponseEntity.ok(ApiResponse.success("like 생성 성공", response));

    }

    // 좋아요 삭제
    @Operation(summary = "좋아요 삭제")
    @DeleteMapping("/posts/{postNo}/like")
    public ResponseEntity<ApiResponse> deleteLike(@RequestHeader Map<String, String> requestHeader,
                                                         @PathVariable("postNo") Long postNo) {

        // 사용자 정보 조회
        UserResponse user = apiUserLikeCommandService.getUser(requestHeader.get("authorization"));
        // LIKE 가 존재하는지 확인하고 LIKE 를 삭제해서 response 반환
        DeleteLikeResponse response = likeCommandService.deleteLikeWithPostNoAndUser(postNo, user);

        return ResponseEntity.ok(ApiResponse.success("like 삭제 성공", response));
    }

}
