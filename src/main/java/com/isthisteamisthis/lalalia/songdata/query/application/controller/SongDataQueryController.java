package com.isthisteamisthis.lalalia.songdata.query.application.controller;

import com.isthisteamisthis.lalalia.common.ApiResponse;
import com.isthisteamisthis.lalalia.perfectscore.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreListResponse;
import com.isthisteamisthis.lalalia.perfectscore.query.application.dto.response.FindPerfectScoreResponse;
import com.isthisteamisthis.lalalia.songdata.query.application.dto.response.FindSongDataListResponse;
import com.isthisteamisthis.lalalia.songdata.query.application.dto.response.FindSongDataResponse;
import com.isthisteamisthis.lalalia.songdata.query.application.service.SongDataQueryService;
import com.isthisteamisthis.lalalia.songdata.query.domain.repository.SongDataRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "노래 데이터 Query API")
@RestController
@RequiredArgsConstructor
public class SongDataQueryController {

    private final SongDataQueryService songDataQueryService;

    @GetMapping("/song-data")
    public ResponseEntity<ApiResponse> getSongDataList() {

        List<FindSongDataListResponse> response = songDataQueryService.getSongDataList();

        return ResponseEntity.ok(ApiResponse.success("노래데이터 전체 조회 성공", response));
    }

    @GetMapping("/song-data/{songDataNo}")
    public ResponseEntity<ApiResponse> getSongDataByNo(@PathVariable("songDataNo") Long songDataNo) {

        FindSongDataResponse response = songDataQueryService.getSongDataByNo(songDataNo);

        return ResponseEntity.ok(ApiResponse.success("노래데이터 상세 조회 성공", response));
    }
}
