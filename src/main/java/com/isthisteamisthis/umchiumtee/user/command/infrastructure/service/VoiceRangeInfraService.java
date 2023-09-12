package com.isthisteamisthis.umchiumtee.user.command.infrastructure.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;

@Service
public class VoiceRangeInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    public Array getMaxRange(MultipartFile maxRangeWav) {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", maxRangeWav)
                .header("Content-Type", "multipart/form-data");

        return webClient.post()
                .uri("/api/range")
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(Array.class)
                .block();  // [주파수, 모시기, 모시기]  => 순서 통일 3가지
    }

    public Array getMinRange(MultipartFile minRangeWav) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", minRangeWav)
                .header("Content-Type", "multipart/form-data");

        return webClient.post()
                .uri("/api/range")
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(Array.class)
                .block();  // [주파수, 모시기, 모시기]  => 순서 통일 3가지
    }
}
