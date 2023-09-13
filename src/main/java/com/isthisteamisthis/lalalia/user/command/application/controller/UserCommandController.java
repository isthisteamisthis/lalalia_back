package com.isthisteamisthis.lalalia.user.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.user.command.application.dto.request.VoiceRangeRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.service.UserCommandService;
import com.isthisteamisthis.lalalia.user.command.infrastructure.service.VoiceRangeInfraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "회원 Command API")
@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;
    private final VoiceRangeInfraService voiceRangeInfraService;
    private final SaveWAVFileService saveWAVFileService;

    @Operation(summary = "최고 음역대 생성")
    @PostMapping("/api/max-voice-range")
    public ResponseEntity<ApiResponse> createMaxVoiceRange(@RequestPart("voice-range") MultipartFile rangeWav) throws IOException {

        Long userNo = 1L;

        saveWAVFileService.saveVoiceRangeFile(rangeWav);

        MaxVoiceRangeResponse maxResponse = voiceRangeInfraService.getMaxRange(userNo, rangeWav.getResource());

        userCommandService.addMaxVoiceRange(userNo, maxResponse);
        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", maxResponse));
    }

    @Operation(summary = "최저 음역대 생성")
    @PostMapping("/api/min-voice-range")
    public ResponseEntity<ApiResponse> createMinVoiceRange(VoiceRangeRequest request, @RequestPart("voice-range") MultipartFile rangeWav) throws IOException {

        Long userNo = 1L;

        saveWAVFileService.saveVoiceRangeFile(rangeWav);

        MinVoiceRangeResponse minResponse = voiceRangeInfraService.getMinRange(userNo, rangeWav.getResource());

        userCommandService.addMinVoiceRange(userNo, minResponse);
        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", minResponse));
    }


}
