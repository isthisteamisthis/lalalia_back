package com.isthisteamisthis.lalalia.user.command.infrastructure.service;

import com.isthisteamisthis.lalalia.rangesongdata.query.application.service.RangeSongDataService;
import com.isthisteamisthis.lalalia.rangesongdata.query.domain.aggregate.entity.RangeSongData;
import com.isthisteamisthis.lalalia.rangesongdata.query.domain.repository.RangeSongDataRepository;
import com.isthisteamisthis.lalalia.user.command.application.dto.request.RecommendationRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.CreateRangeSongResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VoiceRangeInfraService {

    private final RangeSongDataService rangeSongDataService;

    private WebClient webClient = WebClient.builder().baseUrl("http://192.168.0.165:8888").build();

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

        RecommendationRequest requestObject = new RecommendationRequest(high, low);

        List<String> response = webClient.post()
                .uri("/get-recommendations")
                .bodyValue(requestObject)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(List.class)
                .block();

//        List<String> response = new ArrayList<>();
//        response.add("모든날모든순간.wav");
//        response.add("나에게그대만이.wav");

        List<String> filename = new ArrayList<>();

        for (String fullname : response) {
            String newName = fullname.replaceAll("\\.(wav|mp3)","");
            filename.add(newName);
        }

        Map<String, String> map = rangeSongDataService.addArtistMap(filename);

        return new CreateRangeSongResponse(map);
    }

}
