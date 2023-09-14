package com.isthisteamisthis.lalalia.perfectscore.command.infrastructure.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class PerfectScoreInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
    public Float getScoreResult(MultipartFile wavFile) {
//        int cnt = 0;
//
//        Boolean[] result = webClient.post().uri("api/get-perfect-score")
//                .bodyValue(wavFile)
//                .retrieve()
//                .bodyToMono(Boolean[].class)
//                .block();
//
//
//        if(result == null) {
//           throw new IllegalArgumentException("결과값이 넘어오지 않았습니다.");
//        } else {
//            for (Boolean bool : result) {
//                if (bool) {
//                    cnt++;
//                }
//            }
//        }

//        return (float) (cnt * 100) / result.length;
        return 100F;
    }
}
