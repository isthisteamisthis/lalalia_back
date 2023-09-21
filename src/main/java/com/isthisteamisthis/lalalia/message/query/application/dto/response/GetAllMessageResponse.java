package com.isthisteamisthis.lalalia.message.query.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class GetAllMessageResponse {

    private final List<GetMessageListResponse> messageList;

    public static GetAllMessageResponse from(List<GetMessageListResponse> messageList){
        return new GetAllMessageResponse(
                messageList
        );
    }

}
