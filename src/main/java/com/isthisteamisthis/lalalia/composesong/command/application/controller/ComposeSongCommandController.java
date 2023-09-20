package com.isthisteamisthis.lalalia.composesong.command.application.controller;

import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.composesong.command.application.dto.request.CreateComposeSongRequest;
import com.isthisteamisthis.lalalia.composesong.command.application.service.ComposeSongCommandService;
import com.isthisteamisthis.lalalia.composesong.command.infrastructure.service.ComposeSongInfraService;
import com.isthisteamisthis.lalalia.user.command.application.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ComposeSongCommandController {

    private final SaveWAVFileService saveWAVFileService;
    private final ComposeSongInfraService composeSongInfraService;
    private final UserCommandService userCommandService;
    private final ComposeSongCommandService composeSongCommandService;

    @Operation(summary = "AI 데모곡 생성")
    @PostMapping(value = "/api/ai-songs")
    public void createAiDemoSong(@RequestHeader Map<String, String> requestHeader,
                                 @RequestPart("name") String name,
                                 @RequestPart("model") String model,
                                 @RequestPart("octave") String octave,
                                 @RequestPart("index") String index,
                                 @RequestPart("ai-song") MultipartFile aiSongWav) throws IOException {

        String authorizationHeader = requestHeader.get("authorization");

        Long userId = userCommandService.getUserIdFromToken(authorizationHeader);

        CreateComposeSongRequest request = new CreateComposeSongRequest(name, model, octave, index);

        composeSongInfraService.createDemoSong(request, aiSongWav);

    }

    //ai flask 서버에서 완성 후 post 로 전송
    @Operation(summary = "ai 데모곡 생성 결과 저장")
    @PostMapping(value = "/api/created-song")
    public void saveAiDemoSong(@RequestPart Long userId, @RequestPart("file") MultipartFile aiSongWav) throws IOException {

        String fileDirectory = saveWAVFileService.saveAiSongFile(aiSongWav);

        composeSongCommandService.createComposeSong(userId, fileDirectory);

    }
}
