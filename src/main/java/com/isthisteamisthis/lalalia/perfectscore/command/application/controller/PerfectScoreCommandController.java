package com.isthisteamisthis.lalalia.perfectscore.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.request.CreatePerfectScoreRequest;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response.PerfectScoreCommandResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.application.service.PerfectScoreCommandService;
import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.perfectscore.command.infrastructure.service.PerfectScoreInfraService;
import com.isthisteamisthis.lalalia.user.command.infrastructure.service.VoiceRangeInfraService;
import com.isthisteamisthis.lalalia.user.query.application.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Tag(name = "퍼펙트 스코어 Command API")
@RestController
@RequiredArgsConstructor
public class PerfectScoreCommandController {

    private final PerfectScoreCommandService perfectScoreCommandService;
    private final PerfectScoreInfraService perfectScoreInfraService;
    private final SaveWAVFileService saveWAVFileService;
    private final UserQueryService userQueryService;

    @Operation(summary = "퍼펙트 스코어 생성")
    @PostMapping("/perfect-scores")
    public ResponseEntity<ApiResponse> createPerfectScore(@RequestHeader Map<String, String> requestHeader, CreatePerfectScoreRequest request, @RequestPart("file") MultipartFile perfectScoreWav) throws IOException {

        String authorizationHeader = requestHeader.get("authorization");
        Long userId = userQueryService.getUserFromToken(authorizationHeader);

        Long userNo = userQueryService.findUserByUserId(userId).getUserNo();

        String fileDirectory = saveWAVFileService.savePerfectScoreFile(perfectScoreWav);

//        perfectScoreInfraService.getScoreResult(perfectScoreWav.getResource());

        PerfectScoreCommandResponse response = perfectScoreCommandService.createPerfectScore(userNo, request, 100F, fileDirectory);

        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", response));
    }



}
