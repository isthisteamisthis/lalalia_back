package com.isthisteamisthis.lalalia.composesong.query.application.service;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.composesong.query.application.dto.resposne.FindComposeSongListResponse;
import com.isthisteamisthis.lalalia.composesong.query.application.dto.resposne.FindComposeSongResponse;
import com.isthisteamisthis.lalalia.composesong.query.domain.repository.ComposeSongQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComposeSongQueryService {

    private final ComposeSongQueryRepository composeSongQueryRepository;
    @Transactional(readOnly = true)
    public FindComposeSongResponse getComposeSongBySongNo(Long songNo) {

        Optional<ComposeSong> optionalComposeSong = composeSongQueryRepository.findById(songNo);

        if(optionalComposeSong.isPresent()) {
            ComposeSong song = optionalComposeSong.get();
            return FindComposeSongResponse.from(song);
        }
        else throw new IllegalArgumentException("일치하는 데모곡이 없습니다.");
    }

    @Transactional(readOnly = true)
    public FindComposeSongListResponse getComposeSongListByUserNoVO(UserNoVO userNoVO) {

        List<ComposeSong> composeSongList = composeSongQueryRepository.findComposeSongsByUserNoVO(userNoVO);

        return FindComposeSongListResponse.from(composeSongList);
    }

//    private List<FindComposeSongListResponse> getMessageListResponse(List<ComposeSong> composeSongList) {
//
//        List<FindComposeSongListResponse> response = composeSongList.stream().map(
//                composeSong -> {
//
//                    Long sendUserNo = composeSong.getSendUserNoVO().getSendUserNo();
//                    Long getUserNo = composeSong.getGetUserNoVO().getGetUserNo();
//
//                    String sendUserNickname = apiUserMessageQueryService.getNicknameByUserNo(sendUserNo);
//                    String getUserNickname = apiUserMessageQueryService.getNicknameByUserNo(getUserNo);
//
//                    return FindComposeSongListResponse.from();
//
//                }).collect(Collectors.toList());
//
//        return response;
//    }
}
