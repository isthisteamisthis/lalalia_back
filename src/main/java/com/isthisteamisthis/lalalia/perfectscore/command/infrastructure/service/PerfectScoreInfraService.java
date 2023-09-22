package com.isthisteamisthis.lalalia.perfectscore.command.infrastructure.service;

import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.request.CreatePerfectScoreRequest;
import com.isthisteamisthis.lalalia.perfectscore.command.application.dto.response.PerfectScoreInfraResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class PerfectScoreInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("http://192.168.0.55:8888").build();

    public Float getScoreResult(CreatePerfectScoreRequest request, Resource wavFile) {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("id", request.getSongName());
        bodyBuilder.part("music", wavFile);

//        PerfectScoreInfraResponse result = webClient.post()
//                .uri("/")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
//                .accept(MediaType.MULTIPART_FORM_DATA)
//                .retrieve()
//                .bodyToMono(PerfectScoreInfraResponse.class)
//                .block();
//
//        return Float.parseFloat(result.getScore());

        return 100F;
    }
}
