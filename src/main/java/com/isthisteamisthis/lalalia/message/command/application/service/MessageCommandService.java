package com.isthisteamisthis.lalalia.message.command.application.service;

import com.isthisteamisthis.lalalia.message.command.application.dto.request.SendMessageRequest;
import com.isthisteamisthis.lalalia.message.command.application.dto.response.DeleteMessageResponse;
import com.isthisteamisthis.lalalia.message.command.application.dto.response.SendMessageResponse;
import com.isthisteamisthis.lalalia.message.command.domain.aggregate.entity.Message;
import com.isthisteamisthis.lalalia.message.command.domain.aggregate.vo.GetUserNoVO;
import com.isthisteamisthis.lalalia.message.command.domain.aggregate.vo.SendUserNoVO;
import com.isthisteamisthis.lalalia.message.command.domain.repository.MessageCommandRepository;
import com.isthisteamisthis.lalalia.user.query.application.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MessageCommandService {

    private final MessageCommandRepository messageCommandRepository;

    // 메세지 보내기
    @Transactional
    public SendMessageResponse sendMessage(UserResponse sendUser, UserResponse getUser, SendMessageRequest request) {
        // 수신자, 송신자 VO 생성
        SendUserNoVO sendUserNoVO = new SendUserNoVO(sendUser.getUserNo());
        GetUserNoVO getUserNoVO = new GetUserNoVO(getUser.getUserNo());
        // 메세지 저장
        Message message = Message.builder()
                .content(request.getContent())
                .date(new Date())
                .sendUserNoVO(sendUserNoVO)
                .getUserNoVO(getUserNoVO)
                .build();

        messageCommandRepository.save(message);

        return SendMessageResponse.from(message);

    }

    // 메세지 삭제하기
    @Transactional
    public DeleteMessageResponse deleteMessage(UserResponse user, Long messageNo) {
        // messageNo으로 메세지 조회
        Message message = messageCommandRepository.findByMessageNo(messageNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid MessageNo"));
        // 해당 메세지를 작성한 본인이 아닐 경우 예외처리
        if (!message.getGetUserNoVO().getGetUserNo().equals(user.getUserNo())) {
            throw new IllegalArgumentException("");
        }
        // 본인일 경우 메세지 삭제
        messageCommandRepository.delete(message);

        return new DeleteMessageResponse(messageNo);
    }
}
