package com.isthisteamisthis.lalalia.composesong.command.infrastructure.service;

import com.isthisteamisthis.lalalia.composesong.command.application.dto.request.CreateComposeSongRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ComposeSongInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("http://192.168.0.165:8888").build();
    public void createDemoSong(CreateComposeSongRequest request, MultipartFile wavFile){

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("id", request.getIdentifier());
        bodyBuilder.part("music", wavFile.getResource());
        bodyBuilder.part("model", request.getModel());
        bodyBuilder.part("octave", request.getOctave());
        bodyBuilder.part("index", request.getIndex());


        webClient.post()
                .uri("/aicover")
                .contentType(MediaType.MULTIPART_FORM_DATA)  // Set the content type here
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))  // Use fromMultipartData instead of fromValue
                .accept(MediaType.MULTIPART_FORM_DATA)
                .retrieve()
                .toBodilessEntity()
                .subscribe(responseEntity -> {
                    HttpStatus statusCode = responseEntity.getStatusCode();
                    if (statusCode.is2xxSuccessful()) {
                        System.out.println("Request was successful (2xx status code)");
                    } else {
                        System.out.println("Request failed with status code: " + statusCode);
                    }
                }, System.out::println);

    }
}
