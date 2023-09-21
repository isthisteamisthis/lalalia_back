package com.isthisteamisthis.lalalia.songdata.query.application.service;

import com.isthisteamisthis.lalalia.songdata.query.domain.aggregate.entity.SongData;
import com.isthisteamisthis.lalalia.songdata.query.domain.repository.SongDataRepository;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.CreateRangeSongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SongDataService {

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
}
