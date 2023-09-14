package com.isthisteamisthis.lalalia.user.command.infrastructure.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MaxRangeVO;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class VoiceRangeInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("http://192.168.0.55:8888").build();

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

    public MinVoiceRangeResponse getMinRange(Long userNo, Resource minRangeWav) {

        MinVoiceRangeResponse result = webClient.post()
                .uri("/upload-low")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", minRangeWav))
                .retrieve()
                .bodyToMono(MinVoiceRangeResponse.class)
                .block();

        System.out.println("infra service: " + result.toString());

        return new MinVoiceRangeResponse(result.getLowestfrequency(), result.getNote(), result.getOctave());
    }
}
