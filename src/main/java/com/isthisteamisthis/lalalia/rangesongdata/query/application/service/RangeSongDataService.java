package com.isthisteamisthis.lalalia.rangesongdata.query.application.service;

import com.isthisteamisthis.lalalia.rangesongdata.query.domain.aggregate.entity.RangeSongData;
import com.isthisteamisthis.lalalia.rangesongdata.query.domain.repository.RangeSongDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RangeSongDataService {

    private final RangeSongDataRepository rangeSongDataRepository;

    public Map<String, String> addArtistMap(List<String> filenames) {
        Map<String, String> fileMap = new HashMap<>();

        for (String filename : filenames) {
            RangeSongData songData = rangeSongDataRepository.findBySongName(filename);

            if (songData != null) {
                fileMap.put(filename, songData.getArtistName());
            } else {
                fileMap.put(filename, "NULL"); // Handle case when no matching songName is found.
            }
        }

        return fileMap;
    }
}
