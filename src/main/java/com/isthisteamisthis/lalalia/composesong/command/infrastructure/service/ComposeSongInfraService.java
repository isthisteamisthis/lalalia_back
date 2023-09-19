package com.isthisteamisthis.lalalia.composesong.command.infrastructure.service;

import com.isthisteamisthis.lalalia.composesong.command.application.dto.request.ComposeSongInfraRequest;
import com.isthisteamisthis.lalalia.composesong.command.application.dto.request.CreateComposeSongRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.request.RecommendationRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.CreateRangeSongResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ComposeSongInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("http://192.168.0.165:8888").build();
    public void createDemoSong(CreateComposeSongRequest request, MultipartFile wavFile) throws IOException {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("music", wavFile.getResource());
        bodyBuilder.part("model", request.getModel());
        bodyBuilder.part("octave", request.getOctave());
        bodyBuilder.part("index", request.getIndex());


        String response = webClient.post()
                .uri("/aicover")
                .contentType(MediaType.MULTIPART_FORM_DATA)  // Set the content type here
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))  // Use fromMultipartData instead of fromValue
                .accept(MediaType.MULTIPART_FORM_DATA)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }
}