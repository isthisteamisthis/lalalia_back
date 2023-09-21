package com.isthisteamisthis.lalalia.composesong.query.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.composesong.query.application.dto.resposne.FindComposeSongListResponse;
import com.isthisteamisthis.lalalia.composesong.query.application.dto.resposne.FindComposeSongResponse;
import com.isthisteamisthis.lalalia.composesong.query.application.service.ComposeSongQueryService;
import com.isthisteamisthis.lalalia.user.query.application.service.UserQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "AI 데모곡 Query API")
@RestController
@RequiredArgsConstructor
public class ComposeSongQueryController {

    private final UserQueryService userQueryService;
    private final ComposeSongQueryService composeSongQueryService;


    @GetMapping("/ai-songs")
    public ResponseEntity<ApiResponse> getComposeSongLIst(@RequestHeader Map<String, String> requestHeader) {
        String authorizationHeader = requestHeader.get("authorization");
        Long userId = userQueryService.getUserFromToken(authorizationHeader);

        Long userNo = userQueryService.findUserByUserId(userId).getUserNo();


        FindComposeSongListResponse response = composeSongQueryService.getComposeSongListByUserNoVO(new UserNoVO(userNo));

        return ResponseEntity.ok(ApiResponse.success("AI 데모곡 전체 조회 성공", response));
    }
    @GetMapping("/ai-songs/{composeSongNo}")
    public ResponseEntity<ApiResponse> getSongBySongNO(@RequestHeader Map<String, String> requestHeader, @PathVariable("composeSongNo") Long songNo) {
        String authorizationHeader = requestHeader.get("authorization");
        Long userId = userQueryService.getUserFromToken(authorizationHeader);

        FindComposeSongResponse response = composeSongQueryService.getComposeSongBySongNo(songNo);

        return ResponseEntity.ok(ApiResponse.success("AI 데모곡 상세 조회 성공", response));
    }
}
