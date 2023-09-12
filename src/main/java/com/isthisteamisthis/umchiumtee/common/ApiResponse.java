package com.isthisteamisthis.umchiumtee.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse {

    private final ApiStatus status;
    private final String message;
    private final Object data;

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(ApiStatus.SUCCESS, message, data);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(ApiStatus.ERROR, message, null);
    }
}
