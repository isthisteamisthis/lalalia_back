package com.isthisteamisthis.lalalia.perfectscore.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.request.CreatePerfectScoreRequest;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response.PerfectScoreCommandResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.application.service.PerfectScoreCommandService;
import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.perfectscore.command.infrastructure.service.PerfectScoreInfraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "퍼펙트 스코어 Command API")
@RestController
@RequiredArgsConstructor
public class PerfectScoreCommandController {

    private final PerfectScoreCommandService perfectScoreCommandService;
    private final PerfectScoreInfraService perfectScoreInfraService;
    private final SaveWAVFileService saveWAVFileService;

    @Operation(summary = "퍼펙트 스코어 생성")
    @PostMapping("/api/perfect-scores")
    public ResponseEntity<ApiResponse> createPerfectScore(CreatePerfectScoreRequest request, @RequestPart("perfect-score") MultipartFile perfectScoreWav) throws IOException {

        //음성 파일 저장
        String fileDirectory = saveWAVFileService.savePerfectScoreFile(perfectScoreWav);   //파일 위치 리턴받기
        Float result = perfectScoreInfraService.getScoreResult(perfectScoreWav);
//        Float result = 100F;

        PerfectScoreCommandResponse response = perfectScoreCommandService.createPerfectScore(request, result, fileDirectory);

        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", response));
    }



}
