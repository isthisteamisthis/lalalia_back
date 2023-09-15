package com.isthisteamisthis.lalalia.user.command.infrastructure.service;

import com.isthisteamisthis.lalalia.user.command.application.dto.response.CreateRangeSongResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoiceRangeInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("http://192.168.0.152:8888").build();

    public MaxVoiceRangeResponse getMaxRange(Long userNo, Resource maxRangeWav) {

         MaxVoiceRangeResponse result = webClient.post()
                .uri("/upload-high")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", maxRangeWav))
                .retrieve()
                .bodyToMono(MaxVoiceRangeResponse.class)
                .block();

        return new MaxVoiceRangeResponse(result.getHighestfrequency(), result.getNote(), result.getOctave());
    }

    public MinVoiceRangeResponse getMinRange(Long userNo, Resource minRangeWav) throws IOException {

        MinVoiceRangeResponse result = webClient.post()
                .uri("/upload-low")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", minRangeWav))
                .retrieve()
                .bodyToMono(MinVoiceRangeResponse.class)
                .block();

        return new MinVoiceRangeResponse(result.getLowestfrequency(), result.getNote(), result.getOctave());
    }

    public CreateRangeSongResponse getRecommendSong(Float high, Float low) {

        String request = "{\"high\":" + high + ",\"low\":" + low + "}";

        System.out.println("request = " + request);

//        CreateRangeSongResponse response = webClient.post()
//                .uri("")
//                .bodyValue(request)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(CreateRangeSongResponse.class)
//                .block();

        List<String> example = new ArrayList<>();
        example.add("모든날모든순간.wav");
        example.add("나에게그대만이.wav");

        return new CreateRangeSongResponse(example);
    }
}
