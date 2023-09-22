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

        SendUserNoVO sendUserNoVO = new SendUserNoVO(sendUser.getUserNo());
        GetUserNoVO getUserNoVO = new GetUserNoVO(getUser.getUserNo());

        Message message = Message.builder()
                .content(request.getContent())
                .date(new Date())
                .sendUserNoVO(sendUserNoVO)
                .getUserNoVO(getUserNoVO)
                .build();

        messageCommandRepository.save(message);

        return SendMessageResponse.from(message);

    }

    @Transactional
    public DeleteMessageResponse deleteMessage(UserResponse user, Long messageNo) {

        Message message = messageCommandRepository.findByMessageNo(messageNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid MessageNo"));

        if (!message.getGetUserNoVO().getGetUserNo().equals(user.getUserNo())) {
            throw new IllegalArgumentException("");
        }

        messageCommandRepository.delete(message);

        return new DeleteMessageResponse(messageNo);
    }
}
