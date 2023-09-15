package com.isthisteamisthis.lalalia.perfectscore.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.request.CreatePerfectScoreRequest;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response.PerfectScoreCommandResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.application.service.PerfectScoreCommandService;
import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.perfectscore.command.infrastructure.service.PerfectScoreInfraService;
import com.isthisteamisthis.lalalia.user.command.infrastructure.service.VoiceRangeInfraService;
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
    private final VoiceRangeInfraService voiceRangeInfraService;

    @Operation(summary = "퍼펙트 스코어 생성")
    @PostMapping("/api/perfect-scores")
    public ResponseEntity<ApiResponse> createPerfectScore(CreatePerfectScoreRequest request, @RequestPart("perfect-score") MultipartFile perfectScoreWav) throws IOException {

//        System.out.println("rangeWav = " + perfectScoreWav.getSize());

//        long minUploadSize = 1024 * 1024;
//        if(perfectScoreWav.getSize() < minUploadSize) {
//            System.out.println(" 작다!! ");
//            perfectScoreWav = voiceRangeInfraService.increaseFileSize(perfectScoreWav);
//        }

 //        Float result = perfectScoreInfraService.getScoreResult(perfectScoreWav.getResource());
        String fileDirectory = saveWAVFileService.savePerfectScoreFile(perfectScoreWav);
        PerfectScoreCommandResponse response = perfectScoreCommandService.createPerfectScore(request, 100F, fileDirectory);

        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", response));
    }



}
