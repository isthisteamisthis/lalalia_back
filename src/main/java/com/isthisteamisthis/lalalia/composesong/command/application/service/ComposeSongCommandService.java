package com.isthisteamisthis.lalalia.composesong.command.application.service;

import com.isthisteamisthis.lalalia.composesong.command.domain.repository.ComposeSongCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ComposeSongCommandService {

    private final ComposeSongCommandRepository composeSongCommandRepository;

    @Transactional
    public void createComposeSong(Long userId, String fileDirectory) {


    }
}
