package com.isthisteamisthis.lalalia.message.query.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.message.query.application.dto.response.GetMessageListResponse;
import com.isthisteamisthis.lalalia.message.query.application.dto.response.GetMessageResponse;
import com.isthisteamisthis.lalalia.message.query.application.service.MessageQueryService;
import com.isthisteamisthis.lalalia.message.query.infrastructure.service.ApiUserMessageQueryService;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MessageQueryController {

    private final MessageQueryService messageQueryService;
    private final ApiUserMessageQueryService apiUserMessageQueryService;

    // 사용자가 받은 메세지 리스트 조회 : 날짜 내림차순(최신순)
    @Operation(summary = "사용자가 받은 메세지 리스트 조회 : 날짜 내림차순(최신순)")
    @GetMapping("/messages/received")
    public ResponseEntity<ApiResponse> getAllReceivedMessage(@RequestHeader Map<String, String> requestHeader) {
        // 사용자 정보
        UserResponse user = apiUserMessageQueryService.getUser(requestHeader.get("authorization"));
        // 받은 메세지 리스트 조회해서 가져오기
        List<GetMessageListResponse> response = messageQueryService.getAllReceivedMessage(user);

        return ResponseEntity.ok(ApiResponse.success("받은 메세지 리스트 조회 성공", response));

    }

    // 사용자가 보낸 메세지 리스트 조회 : 날짜 내림차순(최신순)
    @Operation(summary = "사용자가 보낸 메세지 리스트 조회 : 날짜 내림차순(최신순)")
    @GetMapping("/messages/sent")
    public ResponseEntity<ApiResponse> getAllSentMessage(@RequestHeader Map<String, String> requestHeader) {
        // 사용자 정보
        UserResponse user = apiUserMessageQueryService.getUser(requestHeader.get("authorization"));
        // 보낸 메세지 리스트 조회해서 가져오기
        List<GetMessageListResponse> response = messageQueryService.getAllSentMessage(user);

        return ResponseEntity.ok(ApiResponse.success("보낸 메세지 리스트 조회 성공", response));

    }

    // 사용자가 받은 메세지 상세 조회
    @Operation(summary = "사용자가 받은 메세지 상세 조회")
    @GetMapping("/messages/{messageNo}")
    public ResponseEntity<ApiResponse> getMessage(@RequestHeader Map<String, String> requestHeader,
                                                  @PathVariable("messageNo") Long messageNo) {

        // 사용자 정보
        UserResponse user = apiUserMessageQueryService.getUser(requestHeader.get("authorization"));
        // 해당 메세지를 조회해서 가져오기
        GetMessageResponse response = messageQueryService.getMessage(messageNo);

        return ResponseEntity.ok(ApiResponse.success("메세지 상세 조회 성공", response));
    }
}
