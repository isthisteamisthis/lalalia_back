package com.isthisteamisthis.lalalia.composesong.command.application.service;

import com.isthisteamisthis.lalalia.composesong.command.application.dto.request.CreateComposeSongRequest;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.composesong.command.domain.repository.ComposeSongCommandRepository;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.entity.User;
import com.isthisteamisthis.lalalia.user.command.domain.aggregate.vo.MinRangeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComposeSongCommandService {

    private final ComposeSongCommandRepository composeSongCommandRepository;

    @Transactional
    public void createComposeSong(User user, CreateComposeSongRequest request, String imgFilePath, String originalFilePath) {

        System.out.println("request = " + request.getIdentifier());
        System.out.println("UUID.fromString(request.getIdentifier()) = " + UUID.fromString(request.getIdentifier()));

        UUID id = UUID.fromString(request.getIdentifier());

        ComposeSong composeSong = ComposeSong.builder()
                .composeSongNo(id)
                .title(request.getName())
                .imgFile(imgFilePath)
                .originalFile(originalFilePath)
                .userNoVO(new UserNoVO(user.getUserNo()))
                .build();

        composeSongCommandRepository.save(composeSong);
    }

    @Transactional
    public void addAiSongFile(ComposeSong composeSong, String fileDirectory) {
        composeSong.addAiSongFile(fileDirectory);
    }
}
