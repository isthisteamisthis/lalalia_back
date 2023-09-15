package com.isthisteamisthis.lalalia.composesong.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.request.CreatePerfectScoreRequest;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response.PerfectScoreCommandResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ComposeSongCommandController {

    private final SaveWAVFileService saveWAVFileService;
    @Operation(summary = "AI 데모곡 생성")
    @PostMapping("/api/ai-songs")
    public void createPerfectScore(CreatePerfectScoreRequest request, @RequestPart("ai-song") MultipartFile aiSongWav) throws IOException {

        String fileDirectory = saveWAVFileService.saveAiSongFile(aiSongWav);

    }
}
