package com.isthisteamisthis.lalalia.songdata.query.application.service;

import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.entity.PerfectScore;
import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreListResponse;
import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreResponse;
import com.isthisteamisthis.lalalia.songdata.query.application.dto.response.FindSongDataListResponse;
import com.isthisteamisthis.lalalia.songdata.query.application.dto.response.FindSongDataResponse;
import com.isthisteamisthis.lalalia.songdata.query.domain.aggregate.entity.SongData;
import com.isthisteamisthis.lalalia.songdata.query.domain.repository.SongDataRepository;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.CreateRangeSongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongDataQueryService {

    private final SongDataRepository songDataRepository;

    public Map<String, String> addImageUrl(List<String> filenames) {
        Map<String, String> fileMap = new HashMap<>();

        for (String filename : filenames) {
            SongData songData = songDataRepository.findBySongName(filename);

            if (songData != null) {
                fileMap.put(filename, songData.getImageUrl());
            } else {
                fileMap.put(filename, "찾을 수 없음");
            }
        }

        return fileMap;
    }


    public CreateRangeSongResponse getMapFromString(String songLists) {

        List<String> filenames = List.of(songLists.split(","));

        Map<String, String> map = addImageUrl(filenames);

        return new CreateRangeSongResponse(map);
    }

    @Transactional(readOnly = true)
    public List<FindSongDataListResponse> getSongDataList() {

        List<SongData> songDataList = songDataRepository.findAll();

        return getFindSongDataListResponse(songDataList);
    }

    public List<FindSongDataListResponse> getFindSongDataListResponse(List<SongData> songDataList) {
        List<FindSongDataListResponse> response = songDataList.stream().map(
                songData -> {

                    Long songDataNo = songData.getSongDataNo();
                    String  songName = songData.getSongName();
                    String artistName = songData.getArtistName();
                    String imgUrl = songData.getImageUrl();

                    return FindSongDataListResponse.from(songDataNo, songName, artistName, imgUrl);

                }).collect(Collectors.toList());

        return response;
    }

    @Transactional
    public FindSongDataResponse getSongDataByNo(Long songDataNo) {

        Optional<SongData> optionalSongData = songDataRepository.findById(songDataNo);

        if(optionalSongData.isPresent()) {
            SongData songData = optionalSongData.get();
            return FindSongDataResponse.from(songData);
        }
        else throw new IllegalArgumentException("일치하는 노래 데이터가 없습니다.");
    }
}
