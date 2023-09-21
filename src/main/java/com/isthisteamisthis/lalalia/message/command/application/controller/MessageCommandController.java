package com.isthisteamisthis.lalalia.message.command.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.message.command.application.dto.request.SendMessageRequest;
import com.isthisteamisthis.lalalia.message.command.application.dto.response.DeleteMessageResponse;
import com.isthisteamisthis.lalalia.message.command.application.dto.response.SendMessageResponse;
import com.isthisteamisthis.lalalia.message.command.application.service.MessageCommandService;
import com.isthisteamisthis.lalalia.message.command.infrastructure.service.ApiUserMessageCommandService;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MessageCommandController {

    private final ApiUserMessageCommandService apiUserMessageCommandService;
    private final MessageCommandService messageCommandService;

    // 메세지 보내기
    @PostMapping("/{userNo}/message")
    public ResponseEntity<ApiResponse> sendMessageToUser(@RequestHeader Map<String, String> requestHeader,
                                                         @PathVariable("userNo") Long getUserNo,
                                                         @RequestBody SendMessageRequest request) {
        // 메세지 보내는 사용자
        UserResponse sendUser = apiUserMessageCommandService.getUser(requestHeader.get("authorization"));
        // 메세지 받는 사용자
        UserResponse getUser = apiUserMessageCommandService.getUserByUserNo(getUserNo);
        // 메세지 저장
        SendMessageResponse response = messageCommandService.sendMessage(sendUser, getUser, request);

        return ResponseEntity.ok(ApiResponse.success("메세지 전송 성공", response));

    }

    // 메세지 삭제하기
    @DeleteMapping("/messages/{messageNo}")
    public ResponseEntity<ApiResponse> deleteMessage(@RequestHeader Map<String, String> requestHeader,
                                                     @PathVariable("messageNo") Long messageNo) {

        UserResponse user = apiUserMessageCommandService.getUser(requestHeader.get("authorization"));

        DeleteMessageResponse response = messageCommandService.deleteMessage(user, messageNo);

        return ResponseEntity.ok(ApiResponse.success("메세지 삭제 성공", response));
    }


}
