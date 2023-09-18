package com.isthisteamisthis.lalalia.composesong.command.application.controller;

import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.composesong.command.application.dto.request.CreateComposeSongRequest;
import com.isthisteamisthis.lalalia.composesong.command.infrastructure.service.ComposeSongInfraService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ComposeSongCommandController {

    private final SaveWAVFileService saveWAVFileService;
    private final ComposeSongInfraService composeSongInfraService;
    @Operation(summary = "AI 데모곡 생성")
    @PostMapping(value = "/api/ai-songs")
//    public void createAiDemoSong(@RequestPart("request")CreateComposeSongRequest request, @RequestPart("aisong") MultipartFile aiSongWav) throws IOException {
    public void createAiDemoSong(@RequestPart("name") String name,
                                 @RequestPart("model") String model,
                                 @RequestPart("octave") String octave,
                                 @RequestPart("index") String index,
                                 @RequestPart("ai-song") MultipartFile aiSongWav) throws IOException {

        CreateComposeSongRequest request = new CreateComposeSongRequest(name, model,octave,index);

        composeSongInfraService.createDemoSong(request, aiSongWav);

    }

    @Operation(summary = "ai 데모곡 생성 결과 저장")
    @PostMapping(value = "/api/created-song")
    public void saveAiDemoSong(@RequestPart("file") MultipartFile aiSongWav) throws IOException {

        String fileDirectory = saveWAVFileService.saveAiSongFile(aiSongWav);

    }
}
