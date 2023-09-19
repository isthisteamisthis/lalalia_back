package com.isthisteamisthis.lalalia.rangesongdata.query.application.service;

import com.isthisteamisthis.lalalia.rangesongdata.query.domain.aggregate.entity.RangeSongData;
import com.isthisteamisthis.lalalia.rangesongdata.query.domain.repository.RangeSongDataRepository;
import com.isthisteamisthis.lalalia.user.command.application.dto.response.CreateRangeSongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RangeSongDataService {

    private final RangeSongDataRepository rangeSongDataRepository;

    public Map<String, String> addImageUrl(List<String> filenames) {
        Map<String, String> fileMap = new HashMap<>();

        for (String filename : filenames) {
            RangeSongData songData = rangeSongDataRepository.findBySongName(filename);

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
