package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CategoryResponse {

    private final String category;

    public String getCategory() {
        return category;
    }

}
