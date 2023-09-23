package com.isthisteamisthis.lalalia.message.query.application.service;

import com.isthisteamisthis.lalalia.message.command.domain.aggregate.entity.Message;
import com.isthisteamisthis.lalalia.message.command.domain.aggregate.vo.GetUserNoVO;
import com.isthisteamisthis.lalalia.message.command.domain.aggregate.vo.SendUserNoVO;
import com.isthisteamisthis.lalalia.message.query.application.dto.response.GetMessageListResponse;
import com.isthisteamisthis.lalalia.message.query.application.dto.response.GetMessageResponse;
import com.isthisteamisthis.lalalia.message.query.domain.repository.MessageQueryRepository;
import com.isthisteamisthis.lalalia.message.query.infrastructure.service.ApiUserMessageQueryService;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageQueryService {

    private final MessageQueryRepository messageQueryRepository;
    private final ApiUserMessageQueryService apiUserMessageQueryService;

    // 하나의 메세지 상세 조회
    @Transactional(readOnly = true)
    public GetMessageResponse getMessage(Long messageNo) {
        // messageNo로 메세지 조회
        Message message = messageQueryRepository.findByMessageNo(messageNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid MessageNo"));

        // 메세지를 보낸 사용자와 받은 사용자의 닉네임
        String getUserNickname = apiUserMessageQueryService.getNicknameByUserNo(message.getGetUserNoVO().getGetUserNo());
        String sendUserNickname = apiUserMessageQueryService.getNicknameByUserNo(message.getSendUserNoVO().getSendUserNo());

        return GetMessageResponse.from(message, getUserNickname, sendUserNickname);

    }

    // 사용자가 받은 메세지 리스트 조회
    @Transactional(readOnly = true)
    public List<GetMessageListResponse> getAllReceivedMessage(UserResponse user) {
        // 현재 사용자의 userNo로 getUserNoVO 생성
        GetUserNoVO getUserNoVO = new GetUserNoVO(user.getUserNo());
        // getUserNoVO로 message 리스트 조회
        List<Message> messageList = messageQueryRepository.findMessagesByGetUserNoVOOrderByDateDesc(getUserNoVO);
        //  message list 에 nickname 을 추가해서 getMessageListResponse list 로 변환해서 반환
        return getMessageListResponse(messageList);

    }

    // 사용자가 보낸 메세지 리스트 조회
    @Transactional(readOnly = true)
    public List<GetMessageListResponse> getAllSentMessage(UserResponse user) {
        // 현재 사용자의 userNo로 getUserNoVO 생성
        SendUserNoVO sendUserNoVO = new SendUserNoVO(user.getUserNo());
        // getUserNoVO로 message 리스트 조회
        List<Message> messageList = messageQueryRepository.findMessagesBySendUserNoVOOrderByDateDesc(sendUserNoVO);
        //  message list 에 nickname 을 추가해서 getMessageListResponse list 로 변환해서 반환
        return getMessageListResponse(messageList);

    }

    // message list 에 nickname 을 추가해서 getMessageListResponse list 로 변환
    private List<GetMessageListResponse> getMessageListResponse(List<Message> messageList) {

        List<GetMessageListResponse> response = messageList.stream().map(
                message -> {

                    Long sendUserNo = message.getSendUserNoVO().getSendUserNo();
                    Long getUserNo = message.getGetUserNoVO().getGetUserNo();

                    String sendUserNickname = apiUserMessageQueryService.getNicknameByUserNo(sendUserNo);
                    String getUserNickname = apiUserMessageQueryService.getNicknameByUserNo(getUserNo);

                    return GetMessageListResponse.from(message, sendUserNickname, getUserNickname);

                }).collect(Collectors.toList());

        return response;
    }
}
