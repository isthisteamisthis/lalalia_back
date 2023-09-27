package com.isthisteamisthis.lalalia.post.query.infrastructure.service;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.query.domain.repository.ComposeSongQueryRepository;
import com.isthisteamisthis.lalalia.post.query.application.dto.ComposeSongDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiComposeSongPostQueryService {

    private final ComposeSongQueryRepository composeSongQueryRepository;

    // ComposeSongNo로 ComposeSong 조회
    public ComposeSongDTO findComposeSong(Long composeSongNo) {

        Optional<ComposeSong> optionalComposeSong = composeSongQueryRepository.findByComposeSongNo(composeSongNo);

        if (optionalComposeSong.isEmpty()) {
            throw new IllegalArgumentException("Invalid ComposeSongNo");
        }

        return new ComposeSongDTO(optionalComposeSong.get());

    }
}
