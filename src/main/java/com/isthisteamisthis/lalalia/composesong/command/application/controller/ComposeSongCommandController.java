package com.isthisteamisthis.lalalia.composesong.command.application.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.common.Service.SaveWAVFileService;
import com.isthisteamisthis.lalalia.composesong.command.application.dto.request.CreateComposeSongRequest;
import com.isthisteamisthis.lalalia.composesong.command.application.service.ComposeSongCommandService;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.command.domain.repository.ComposeSongCommandRepository;
import com.isthisteamisthis.lalalia.composesong.command.infrastructure.service.ComposeSongInfraService;
import com.isthisteamisthis.lalalia.user.command.application.service.UserCommandService;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.repository.UserCommandRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ComposeSongCommandController {

    private final SaveWAVFileService saveWAVFileService;
    private final ComposeSongInfraService composeSongInfraService;
    private final UserCommandService userCommandService;
    private final ComposeSongCommandService composeSongCommandService;
    private final UserCommandRepository userCommandRepository;
    private final ComposeSongCommandRepository composeSongCommandRepository;

    @Operation(summary = "AI 데모곡 생성")
    @PostMapping(value = "/ai-songs")
    public ResponseEntity<ApiResponse> createAiDemoSong(@RequestHeader Map<String, String> requestHeader,
                                                        @RequestPart("img") MultipartFile img,
                                                        @RequestPart("name") String name,
                                                        @RequestPart("model") String model,
                                                        @RequestPart("octave") String octave,
                                                        @RequestPart("index") String index,
                                                        @RequestPart("song") MultipartFile file) throws IOException, FirebaseAuthException {

        UUID identifier = UUID.randomUUID();

        String authorizationHeader = requestHeader.get("authorization");
        Long userId = userCommandService.getUserIdFromToken(authorizationHeader);

        System.out.println("userId = " + userId);
        Optional<User> optionalUser = userCommandRepository.findByUserId(userId);


        String originalFilePath = saveWAVFileService.saveAiOriginalFile(file);
        String imgFilePath = saveWAVFileService.saveCoverImg(img);

        CreateComposeSongRequest request = new CreateComposeSongRequest(identifier.toString(), name, model, octave, index);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            composeSongCommandService.createComposeSong(user, request, imgFilePath, originalFilePath);
            composeSongInfraService.createDemoSong(request, file);

            return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", identifier.toString()));
        }  else return ResponseEntity.ok(ApiResponse.error("등록에 실패하였습니다."));

    }

    //ai flask 서버에서 완성 후 post 로 전송
    @Operation(summary = "ai 데모곡 생성 결과 저장")
    @PostMapping(value = "/api/created-song")
    public ResponseEntity<ApiResponse> saveAiDemoSong(@RequestPart("id") String identifier,
                                                      @RequestPart("file") MultipartFile aiSongWav) throws IOException {

        Optional<ComposeSong> optionalComposeSong = composeSongCommandRepository.findComposeSongByIdentifier(UUID.fromString(identifier));

        if(optionalComposeSong.isPresent()) {
            ComposeSong composeSong = optionalComposeSong.get();
            String fileDirectory = saveWAVFileService.saveAiSongFile(aiSongWav);
            composeSongCommandService.addAiSongFile(composeSong, fileDirectory);
            return ResponseEntity.ok(ApiResponse.success("성공적으로 등록되었습니다.", fileDirectory));
        }  else return ResponseEntity.ok(ApiResponse.error("등록에 실패하였습니다."));

    }
}
