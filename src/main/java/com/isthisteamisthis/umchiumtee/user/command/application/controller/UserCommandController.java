package com.isthisteamisthis.umchiumtee.user.command.application.controller;

import com.isthisteamisthis.umchiumtee.common.ApiResponse;
import com.isthisteamisthis.umchiumtee.common.Service.SaveWAVFileService;
import com.isthisteamisthis.umchiumtee.user.command.application.dto.request.VoiceRangeRequest;
import com.isthisteamisthis.umchiumtee.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.umchiumtee.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.umchiumtee.user.command.application.service.UserCommandService;
import com.isthisteamisthis.umchiumtee.user.command.infrastructure.service.VoiceRangeInfraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "회원 Command API")
@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;
    private final VoiceRangeInfraService voiceRangeInfraService;

    @Operation(summary = "최고 음역대 생성")
    @PostMapping("/api/max-voice-range")
    public ResponseEntity<ApiResponse> createMaxVoiceRange(VoiceRangeRequest request, @RequestPart("voice-range") MultipartFile rangeWav) throws IOException {

        MaxVoiceRangeResponse maxResponse = voiceRangeInfraService.getMaxRange(request.getUserNo(), rangeWav);

        userCommandService.addMaxVoiceRange(maxResponse);
        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", maxResponse));
    }

    @Operation(summary = "최저 음역대 생성")
    @PostMapping("/api/min-voice-range")
    public ResponseEntity<ApiResponse> createMinVoiceRange(VoiceRangeRequest request, @RequestPart("voice-range") MultipartFile rangeWav) throws IOException {

        MinVoiceRangeResponse minResponse = voiceRangeInfraService.getMinRange(request.getUserNo(), rangeWav);

        userCommandService.addMinVoiceRange(minResponse);
        return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", minResponse));
    }


}
