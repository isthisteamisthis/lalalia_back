package com.isthisteamisthis.lalalia.user.command.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CategoryRequest implements Serializable {

    @JsonProperty("category")
    private final String category;

    public String getCategory() {
        return category;
    }

}
