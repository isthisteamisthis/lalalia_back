package com.isthisteamisthis.lalalia.user.command.application.service;

import com.isthisteamisthis.lalalia.user.command.application.dto.request.CreateUserRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.UserCommandResponse;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserCommandService {

    private final UserCommandRepository userCommandRepository;
    @Transactional
    public UserCommandResponse createNewUser(CreateUserRequest userDTO) {
        //음역대 측정 메소드 결과물 -> 빌더 사용 시 넣어주기

        User user = userCommandRepository.save(User.builder().build());  // 카카오 로그인 시 넘어오는 정보 넣기

        return UserCommandResponse.from(user);
    }

    @Transactional
    public void addMaxVoiceRange(MaxVoiceRangeResponse response) {

        User user = userCommandRepository.findById(response.getUserNo()).orElseThrow(
                NoSuchElementException::new);

        user.addMaxVoiceRange(response.getMaxRangeVO());
    }

    @Transactional
    public void addMinVoiceRange(MinVoiceRangeResponse response) {

        User user = userCommandRepository.findById(response.getUserNo()).orElseThrow(
                NoSuchElementException::new);

        user.addMinVoiceRange(response.getMinRangeVO());
    }


}
