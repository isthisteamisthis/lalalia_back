package com.isthisteamisthis.lalalia.user.command.application.service;

import com.isthisteamisthis.lalalia.user.command.application.dto.request.CreateUserRequest;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MaxVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.MinVoiceRangeResponse;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.UserCommandResponse;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MaxRangeVO;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
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

        User user = userCommandRepository.save(User.builder().build());

        return UserCommandResponse.from(user);
    }

    @Transactional
    public void addMaxVoiceRange(Long id, MaxVoiceRangeResponse response) {

        User user = userCommandRepository.findById(1L).orElseThrow(
                NoSuchElementException::new);

        user.addMaxVoiceRange(new MaxRangeVO(Float.parseFloat(response.getHighestfrequency()), response.getNote(), response.getOctave()));
    }

    @Transactional
    public void addMinVoiceRange(Long id, MinVoiceRangeResponse response) {

        User user = userCommandRepository.findById(1L).orElseThrow(
                NoSuchElementException::new);

        user.addMinVoiceRange(new MinRangeVO(Float.parseFloat(response.getLowestfrequency()), response.getNote(), response.getOctave()));
    }


}
