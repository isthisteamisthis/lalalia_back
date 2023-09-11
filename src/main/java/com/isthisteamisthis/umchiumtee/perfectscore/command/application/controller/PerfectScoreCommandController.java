package com.isthisteamisthis.umchiumtee.perfectscore.command.application.controller;

import com.isthisteamisthis.umchiumtee.common.ApiResponse;
import com.isthisteamisthis.umchiumtee.perfectscore.command.application.dto.request.CreatePerfectScoreRequest;
import com.isthisteamisthis.umchiumtee.perfectscore.command.application.dto.response.PerfectScoreCommandResponse;
import com.isthisteamisthis.umchiumtee.perfectscore.command.application.service.PerfectScoreCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "퍼펙트 스코어 Command API")
@RestController
@RequiredArgsConstructor
public class PerfectScoreCommandController {

    private final PerfectScoreCommandService perfectScoreCommandService;

    @Operation(summary = "퍼펙트 스코어 생성")
    @PostMapping("/api/perfect-scores")
    public ResponseEntity<ApiResponse> createPerfectScore(CreatePerfectScoreRequest request) {

        Float score = 100F;

        //ai server 호출
        //점수 저장
        //score 점수 설정

        PerfectScoreCommandResponse response = perfectScoreCommandService.createPerfectScore(request,score);

        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", response));
    }
}
