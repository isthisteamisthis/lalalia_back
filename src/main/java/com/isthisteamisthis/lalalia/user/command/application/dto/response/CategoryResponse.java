package com.isthisteamisthis.lalalia.user.command.application.dto.response;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CategoryResponse {
    // 작곡가 / 가수 선택에 대한 응답
    private final String category;

    public String getCategory() {
        return category;
    }

}
