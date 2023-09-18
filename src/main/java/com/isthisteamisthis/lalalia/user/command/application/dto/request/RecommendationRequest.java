package com.isthisteamisthis.lalalia.user.command.application.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationRequest {
    private Float high;
    private Float low;

    public RecommendationRequest(Float high, Float low) {
        this.high = high;
        this.low = low;
    }

}

