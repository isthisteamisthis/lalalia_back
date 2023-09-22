package com.isthisteamisthis.lalalia.perfectscore.query.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreListResponse;
import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreResponse;
import com.isthisteamisthis.lalalia.perfectscore.query.application.service.PerfectScoreQueryService;
import com.isthisteamisthis.lalalia.user.query.application.service.UserQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name = "퍼펙트 스코어 Query API")
@RestController
@RequiredArgsConstructor
public class PerfectScoreQueryController {

    private final PerfectScoreQueryService perfectScoreQueryService;
    private final UserQueryService userQueryService;
    @GetMapping("/perfect-scores")
    public ResponseEntity<ApiResponse> getPerfectScoreList(@RequestHeader Map<String, String> requestHeader) {
        String authorizationHeader = requestHeader.get("authorization");
        Long userId = userQueryService.getUserFromToken(authorizationHeader);

        Long userNo = userQueryService.findUserByUserId(userId).getUserNo();

        List<FindPerfectScoreListResponse> response = perfectScoreQueryService.getPerfectScoreListByUserNoVO(new UserNoVO(userNo));

        return ResponseEntity.ok(ApiResponse.success("퍼펙트 스코어 전체 조회 성공", response));
    }

    @GetMapping("/perfect-scores/{perfectScoreNo}")
    public ResponseEntity<ApiResponse> getPerfectScoreByPerfectScoreNo(@RequestHeader Map<String, String> requestHeader, @PathVariable("perfectScoreNo") Long perfectScoreNo) {
        String authorizationHeader = requestHeader.get("authorization");
        Long userId = userQueryService.getUserFromToken(authorizationHeader);

        Long userNo = userQueryService.findUserByUserId(userId).getUserNo();

        FindPerfectScoreResponse response = perfectScoreQueryService.getPerfectScoreByNo(perfectScoreNo);

        return ResponseEntity.ok(ApiResponse.success("퍼펙트 스코어 상세 조회 성공", response));
    }

}
