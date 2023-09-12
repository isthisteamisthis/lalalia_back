package com.isthisteamisthis.lalalia.user.command.infrastructure.service;

import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MaxRangeVO;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class VoiceRangeInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    public MaxVoiceRangeResponse getMaxRange(Long userNo, MultipartFile maxRangeWav) {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", maxRangeWav)
                .header("Content-Type", "multipart/form-data");

        String[] result = webClient.post()
                .uri("/api/range")
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String[].class)
                .block();  // [주파수, 음, 옥타브]  => 순서 통일 3가지

        return new MaxVoiceRangeResponse(userNo, new MaxRangeVO(Float.parseFloat(result[0]), result[1], result[2]));
    }

    public MinVoiceRangeResponse getMinRange(Long userNo, MultipartFile minRangeWav) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", minRangeWav)
                .header("Content-Type", "multipart/form-data");

        String[] result = webClient.post()
                .uri("/api/range")
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String[].class)
                .block();  // [주파수, 음, 옥타브]  => 순서 통일 3가지

        return new MinVoiceRangeResponse(userNo, new MinRangeVO(Float.parseFloat(result[0]), result[1], result[2]));
    }
}
