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

    public Map<String, String> findArtistNamesByFilename(List<String> filenames) {
        Map<String, String> filenameToArtistMap = new HashMap<>();

        for (String filename : filenames) {
            RangeSongData songData = rangeSongDataRepository.findBySongName(filename);

            if (songData != null) {
                filenameToArtistMap.put(filename, songData.getArtistName());
            } else {
                filenameToArtistMap.put(filename, "NULL"); // Handle case when no matching songName is found.
            }
        }

        return filenameToArtistMap;
    }
}
